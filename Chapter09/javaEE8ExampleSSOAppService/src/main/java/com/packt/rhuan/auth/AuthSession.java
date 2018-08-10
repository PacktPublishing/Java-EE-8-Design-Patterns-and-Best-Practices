package com.packt.rhuan.auth;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@ApplicationScoped
public class AuthSession {

    private Map<String,Auth> authenticated;

    private Map<String,Auth> dataSource;

    @PostConstruct
    public void init(){

        authenticated = new HashMap<>();
        dataSource = new HashMap<>();

        for(int i = 1; i <= 50; i++)
            dataSource.put("login"+i, new Auth("login"+i, "123456") );

    }

    public AuthSession putAuthenticated(String key, Auth auth){

        authenticated.put(key, auth);
        return this;

    }

    public AuthSession removeAuthenticated(String key, Auth auth){

        authenticated.remove(key, auth);
        return this;

    }

    public Map<String, Auth> getAuthenticated() {
        return authenticated;
    }

    public Map<String, Auth> getDataSource() {
        return dataSource;
    }

    public Optional<String> getToken(String login, String password){

        for( String key :  authenticated.keySet() ){

            Auth auth = authenticated.get( key );
            if( auth.getLogin().equals(login)
                    &&  auth.getPassword().equals( password )){
                return Optional.of(key);
            }

        }

        return Optional.empty();

    }
}
