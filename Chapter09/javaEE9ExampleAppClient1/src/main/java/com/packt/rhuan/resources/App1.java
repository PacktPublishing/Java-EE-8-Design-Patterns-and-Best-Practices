package com.packt.rhuan.resources;


import com.packt.rhuan.auth.Auth;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("app1")
public class App1 {

    @Inject
    private Auth auth;

    @GET
    public Response helloWorld( String token ){

        if( !auth.isLogged( token ) ){
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }

        return Response.ok("Hello World. Welcome to App1!").build();

    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response helloWorld(@FormParam("login") String login, @FormParam("password") String password ) {

        if( Objects.isNull(login) || Objects.isNull(password) ){
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        String token = auth.login(login,password);

        return Response
                .ok("Hello World. Welcome to App1!")
                .header("token",token)
                .build();
    }

}
