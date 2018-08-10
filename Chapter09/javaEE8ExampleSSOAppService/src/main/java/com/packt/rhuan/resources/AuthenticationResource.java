package com.packt.rhuan.resources;


import com.packt.rhuan.auth.Auth;
import com.packt.rhuan.auth.AuthSession;
import com.packt.rhuan.utils.TokenUtils;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.Optional;


@Path("auth")
public class AuthenticationResource {

    @Inject
    private AuthSession authSession;


    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response login(@FormParam("login") String login, @FormParam("password") String password ) {

        Optional<String> key = authSession.getToken(login, password);

        if ( key.isPresent() ){

           return Response.ok(key.get()).build();

        }

        if( !authSession.getDataSource().containsKey(login)
                || !authSession.getDataSource()
                .get(login)
                .getPassword()
                .equals( password) )
            return Response.status(Response.Status.UNAUTHORIZED).build();

        String token = TokenUtils.generateToken();
        authSession.putAuthenticated( token, new Auth(login, password, new Date()));

        return Response.ok(token).status(Response.Status.CREATED).build();

    }


    @HEAD
    @Path("/{token}")
    public Response checkAuthentication(@PathParam("token")String token)  {

        if( authSession.getAuthenticated().containsKey( token )){

            return Response.ok().build();

        }

        return Response.status(Response.Status.UNAUTHORIZED).build();

    }


}
