package com.packt.rhuan.resources;

import com.packt.rhuan.interceptors.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;


@Path("helloworld")
public class HelloWorld {

    @GET
    @Authentication(roles = {"user"})
    public Response helloWorld(@Context HttpServletRequest request, @Context HttpServletResponse response){

        return Response
                .ok("Hello World. Welcome to App with validation by authentication interceptor!")
                .build();

    }


}
