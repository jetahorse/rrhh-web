/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.rrhh.web.resource;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import utn.frsf.ofa.cursojava.rrhh.web.resource.ClienteResource;

/**
 *
 * @author federicoaugustotschopp
 */
@ApplicationPath("/api")
public class AplicacionRRHH extends Application{

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes=new HashSet<>();
        classes.add(ClienteResource.class);
        classes.add(EmpleadoResource.class);
        return classes;
    }
    
}
