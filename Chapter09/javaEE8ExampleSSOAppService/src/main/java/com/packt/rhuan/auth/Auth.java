package com.packt.rhuan.auth;

import java.util.Date;

public class Auth {

    private String login;

    private String password;

    private Date loginDate;

    public Auth(){}

    public Auth(String login, String password){

        this.login = login;
        this.password = password;

    }

    public Auth(String login, String password, Date loginDate){

        this.login = login;
        this.password = password;
        this.loginDate = loginDate;

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

}
