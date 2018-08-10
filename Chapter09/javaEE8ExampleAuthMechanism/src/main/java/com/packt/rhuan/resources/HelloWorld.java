package com.packt.rhuan.resources;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;


@Path("helloworld")
public class HelloWorld {

    @Context
    private SecurityContext securityContext;

    @GET
    @Path("/deploymentdescriptor")
    public Response helloWorldWithDeploymentDescriptor( ){

        return Response
                .ok("Hello World. Welcome to App with validation by deployment descriptor!")
                .build();

    }

    @GET
    @Path("/programmatically")
    public Response helloWorldWithProgrammatically() {

        if (!securityContext.isUserInRole("user")) {
            return Response.status(401).header("WWW-Authenticate", "Basic").build();
        }

        return Response
                .ok("Hello World. Welcome to App with validation by programmatically.")
                .build();
    }

}
