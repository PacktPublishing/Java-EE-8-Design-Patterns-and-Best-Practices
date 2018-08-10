package book.jeepatterns.microservice.resource;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * When it is used in Glassfish ...
 * 
 * @author João Carlos
 *
 */

@ApplicationPath ("/ws")
public class ResourceActivator extends ResourceConfig {

    public ResourceActivator () {
    	this.packages("book.jeepatterns.microservice.resource");
    } 
}
