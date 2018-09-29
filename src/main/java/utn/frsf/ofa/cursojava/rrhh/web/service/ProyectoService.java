/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.rrhh.web.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.ApplicationException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utn.frsf.ofa.cursojava.rrhh.web.logica.EmpleadoLogica;
import utn.frsf.ofa.cursojava.rrhh.web.logica.GestionProyectosExcepcion;
import utn.frsf.ofa.cursojava.rrhh.web.logica.PresupuestoSobrepasadoExcepcion;
import utn.frsf.ofa.cursojava.rrhh.web.logica.ProyectoLogica;
import utn.frsf.ofa.cursojava.rrhh.web.logica.ProyectoMaximoEmpleadosExcepcion;
import utn.frsf.ofa.cursojava.rrhh.web.logica.ProyectoSinEmpleadoEfectivoExcepcion;
import utn.frsf.ofa.cursojava.rrhh.web.modelo.Empleado;
import utn.frsf.ofa.cursojava.rrhh.web.modelo.Proyecto;

/**
 *
 * @author federicoaugustotschopp
 */
@Stateless
@ApplicationException(rollback=true)
public class ProyectoService {
    @PersistenceContext(unitName="RRHH_WEB_PU")
    private EntityManager em;
    @Inject
    private EmpleadoLogica logicaEmpleado;
    @Inject
    private ProyectoLogica logicaProyecto;
    
    public Proyecto guardar(Proyecto c) { 
        if(c.getId()==null)
            this.em.persist(c);
        else
            c=this.em.merge(c);
        this.em.flush();
        this.em.refresh(c);
        return c; 
    }

    public Proyecto porId(Integer id) {
        return this.em.find(Proyecto.class, id);
    }

    public void borrar(Integer id) {
        this.em.remove(this.em.find(Proyecto.class, id));
    }

    public List<Proyecto> todos() { 
        return this.em.createQuery("SELECT p FROM Proyecto p").getResultList();

    }
    
    public List<Proyecto> porNombre(String nombre){ 
        return this.em.createQuery("SELECT p FROM Proyecto p WHERE p.nombre like :P_NOMBRE")
                .setParameter("P_NOMBRE", nombre)
                .getResultList();
    }
    
    public void agregarEmpleado(Proyecto p, Empleado e) throws GestionProyectosExcepcion{
        p=this.em.find(Proyecto.class, p.getId());
        e=this.em.find(Empleado.class, e.getId());
        
        if(!logicaProyecto.cupoDisponible(p)) throw new ProyectoMaximoEmpleadosExcepcion();
        if(!logicaProyecto.tieneEmpleadoEfectivo(p) && e.esContratado()) 
                throw new ProyectoSinEmpleadoEfectivoExcepcion();
        if(!logicaEmpleado.puedeSumarseProyectoNuevo(e)) 
            throw new ProyectoMaximoEmpleadosExcepcion();
        if(!logicaProyecto.presupuestoDisponible(p, e)) 
            throw new PresupuestoSobrepasadoExcepcion();
        
        if (p.getEmpleados()==null)
            p.setEmpleados(new ArrayList<Empleado>());
        p.getEmpleados().add(e);
        
    }
}
