/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.rrhh.web.resource;

import java.util.ArrayList;
import java.util.List;
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
import utn.frsf.ofa.cursojava.rrhh.web.modelo.Cliente;
import utn.frsf.ofa.cursojava.rrhh.web.service.ClienteService;

/**
 *
 * @author federicoaugustotschopp
 */
@Stateless
@Path("/cliente")
public class ClienteResource {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject private ClienteService clienteService;
    /*@GET
    public Response listarClientes(){
        return Response.ok("GET").build();
        
    }*/
    
    /*@POST
    public Response crearCliente(Cliente cli){
        System.out.println("CLIENTE RECIBIDO : "+cli);
        return Response.ok("POST"+cli.getNombre()).build();
        
    }*/
    @GET
    public Response listarClientes(@QueryParam("nombre") String nombre){
            List<Cliente> lista = new ArrayList<>();
            if(nombre!=null && nombre.trim().length()>0){
                lista = clienteService.porNombre(nombre);
            }
            else {
                lista = clienteService.todos();
            }
            return Response.ok(lista).build();
    }
    
    @GET
    @Path("{id}")
    public Response buscarPorId(@PathParam("id") Integer idCliente){
        return Response.ok(clienteService.porId(idCliente)).build();
    }
    @POST
    public Response crearCliente(Cliente cli){
        clienteService.guardar(cli);
        return Response.ok().build();
    }
    @PUT
    public Response actualizarCliente(Cliente cli){
        clienteService.guardar(cli);
        return Response.ok("PUT"+cli.getNombre()).build();
    }
    @DELETE
    @Path("{id}")
    public Response borrarCliente(@PathParam("id") Integer idCliente){
        clienteService.borrar(idCliente);
        return Response.ok("DELETE ok").build();
    }

}
