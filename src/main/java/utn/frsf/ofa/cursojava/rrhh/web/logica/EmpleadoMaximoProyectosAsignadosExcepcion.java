/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.rrhh.web.logica;

/**
 *
 * @author federicoaugustotschopp
 */
public class EmpleadoMaximoProyectosAsignadosExcepcion extends GestionProyectosExcepcion{
    public EmpleadoMaximoProyectosAsignadosExcepcion(){
        super("Un empleado no puede asignarse a mas de 2 proyecto");
    }
}
