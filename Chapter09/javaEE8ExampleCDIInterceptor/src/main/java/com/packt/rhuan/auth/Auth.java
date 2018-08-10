package com.packt.rhuan.auth;

public interface Auth {


    public Boolean isAuthorized(String login, String role);

    public Boolean isAuthenticated(String login, String password);

}
