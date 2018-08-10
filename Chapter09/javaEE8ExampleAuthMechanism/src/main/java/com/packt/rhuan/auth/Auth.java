package com.packt.rhuan.auth;

public interface Auth {

    public boolean isLogged(String token);

    public String login(String login, String password);

    String logout(String token);
}
