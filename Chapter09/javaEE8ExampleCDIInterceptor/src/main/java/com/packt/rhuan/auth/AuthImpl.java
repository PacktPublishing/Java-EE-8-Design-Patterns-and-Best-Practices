package com.packt.rhuan.auth;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AuthImpl implements Auth {

    @Inject
    private DataSource dataSource;

    @Override
    public Boolean isAuthorized(String login,String role) {
        return dataSource.readUserRoles( login ).contains( role );
    }

    @Override
    public Boolean isAuthenticated(String login, String password) {
        return dataSource.readUserPassword( login ).contains( password );
    }
}