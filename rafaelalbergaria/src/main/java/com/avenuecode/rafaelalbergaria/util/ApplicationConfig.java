package com.avenuecode.rafaelalbergaria.util;

import java.util.Set;

import javax.ws.rs.core.Application;

import com.avenuecode.rafaelalbergaria.rest.ProductFacadeREST;
import com.avenuecode.rafaelalbergaria.service.RestObjectMapperProvider;
/**
 * 
 * @author Rafael Albergaria
 *
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {	
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        // following code can be used to customize Jersey 2.0 JSON provider:
        try {
        	// Interface utilize for utilized jackson
            Class jsonProvider = Class.forName("org.glassfish.jersey.jackson.JacksonFeature");
            //Class jsonProvider = Class.forName("com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider");
            
            // Class jsonProvider = Class.forName("org.glassfish.jersey.moxy.json.MoxyJsonFeature");
            // Class jsonProvider = Class.forName("org.glassfish.jersey.jettison.JettisonFeature");
            resources.add(jsonProvider);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically re-generated by NetBeans REST support to populate
     * given list with all resources defined in the project.
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ProductFacadeREST.class);
        resources.add(RestObjectMapperProvider.class);
    }

}