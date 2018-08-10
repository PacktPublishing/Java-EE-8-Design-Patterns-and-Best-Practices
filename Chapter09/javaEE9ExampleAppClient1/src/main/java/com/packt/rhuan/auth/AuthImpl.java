package com.packt.rhuan.auth;

import javax.ejb.Stateless;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;

@Stateless
public class AuthImpl implements Auth {

    private String URL = "http://localhost:8080/javaEE8ExampleSSOAppService/resources/auth";

    @Override
    public boolean isLogged(String token) {

        return prepareWebTarget().path("/"+ token)
                .request()
                .head().getStatus() == 200;
    }


    @Override
    public String login(String login, String password) {

        return prepareWebTarget()
                .request()
                .post(Entity.form(new Form("login", login )
                                .param("password", password)),
                      String.class);

    }

    @Override
    public String logout(String token) {

        return prepareWebTarget().path("/"+ token)
                .request()
                .delete(String.class);

    }

    protected WebTarget prepareWebTarget() {

        return ClientBuilder.newClient().target(URL);

    }


}