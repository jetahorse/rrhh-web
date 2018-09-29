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
public class GestionProyectosExcepcion extends Exception {
    public GestionProyectosExcepcion(){
        super("No se pudo procesar la petición");
    }
    public GestionProyectosExcepcion(String msg){
        super(msg);
    }
}
