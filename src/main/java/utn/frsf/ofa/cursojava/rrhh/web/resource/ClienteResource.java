/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.rrhh.web.resource;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import utn.frsf.ofa.cursojava.rrhh.web.modelo.Cliente;

/**
 *
 * @author federicoaugustotschopp
 */
@Stateless
@Path("/cliente")
public class ClienteResource {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @GET
    public Response listarClientes(){
        return Response.ok("GET").build();
        
    }
    @POST
    public Response crearCliente(Cliente cli){
        System.out.println("CLIENTE RECIBIDO : "+cli);
        return Response.ok("POST"+cli.getNombre()).build();
        
    }
}
