package com.packt.rhuan.auth;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class DataSource {

    private Map<String, List<String>> roles;
    private Map<String, String> passwords;

    @PostConstruct
    public void init(){
        roles = new HashMap<>();
        roles.put("rhuan", Arrays.asList("user","admin"));
        roles.put("joao", Arrays.asList("user","admin"));

        passwords = new HashMap<>();
        passwords.put("rhuan", "123456");
        passwords.put("joao", "123456");
    }

    public List<String> readUserRoles(String login){
        return roles.get( login );
    }

    public String readUserPassword(String login){
        return passwords.get( login );
    }
}
