/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.rrhh.web.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import utn.frsf.ofa.cursojava.rrhh.web.logica.GestionProyectosExcepcion;
import utn.frsf.ofa.cursojava.rrhh.web.modelo.Empleado;
import utn.frsf.ofa.cursojava.rrhh.web.modelo.Proyecto;
import utn.frsf.ofa.cursojava.rrhh.web.service.ProyectoService;

/**
 *
 * @author federicoaugustotschopp
 */
@Stateless
@Path("/proyecto")
public class ProyectoResource {
    @Inject
    private ProyectoService proyectoService;
    
    @POST
    public Response crearProyecto(Proyecto p){
        proyectoService.guardar(p);
        return Response.ok().build();
        
    }
    
    @DELETE
    @Path("{id}")
    public Response borrarProyecto(@PathParam("id") Integer idProyecto){
        proyectoService.borrar(idProyecto);
        return Response.ok("DELETE OK").build();
    }
    
    @GET
    public Response listarProyectos(@QueryParam("nombre") String nombre){
        List<Proyecto> lista = new ArrayList<>();
            if(nombre!=null && nombre.trim().length()>0){
                lista = proyectoService.porNombre(nombre);
            }
            else {
                lista = proyectoService.todos();
            }
            return Response.ok(lista).build();
    }
    
    @GET
    @Path("{id}")
    public Response buscarProyectoPorId(@PathParam("id") Integer idProyecto){
        return Response.ok(proyectoService.porId(idProyecto)).build();
    }
        
    @GET
    public Response listarTodos(){
        return Response.ok(proyectoService.todos()).build();
    }
    
    @PUT
    @Path("{id}/empleado")
    public Response agregarEmpleado(@PathParam("id") Integer idProyecto, Empleado e){
        Proyecto p=proyectoService.porId(idProyecto);
        try{
            proyectoService.agregarEmpleado(p, e);
            
        } catch(GestionProyectosExcepcion ex){
            Logger.getLogger(ProyectoResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("FALLO").build();
        }
        return Response.ok("EMPLEADO AGREGADO").build();
    }
}
