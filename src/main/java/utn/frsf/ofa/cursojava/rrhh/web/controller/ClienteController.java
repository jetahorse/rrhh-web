/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.rrhh.web.controller;


import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import utn.frsf.ofa.cursojava.rrhh.web.modelo.Cliente;
import utn.frsf.ofa.cursojava.rrhh.web.service.ClienteService;

/**
 *
 * @author federicoaugustotschopp
 */
@Named
@SessionScoped
public class ClienteController implements Serializable{
    
    @Inject
    private ClienteService clienteService;
    private Cliente cliente;
    public Cliente getCliente(){
        return cliente;
    }
    
    public void setCliente (Cliente c){
        this.cliente=c;
    }
    public String guardar(){
        clienteService.guardar(cliente);
        cliente=null;
        return null;
    }    
    public String nuevo(){ 
        this.cliente = new Cliente(); 
        return null;
    }
    
    
}
