package book.jeepatterns.microservice.service;

import java.text.SimpleDateFormat;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import book.jeepatterns.microservice.model.User;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@LocalBean
public class UserService {

    /**
     * Default constructor. 
     */
    public UserService() {
        // TODO Auto-generated constructor stub
    }
    
    public User getUserById (Long id) {
    	
    	// simulating a call to the database (perhaps a DAO class):
    	User user = new User();
    	user.setCode("F555777");
    	user.setName("Fulano X");
    	// user.setXXX 
    	// ...
    	return user;
    }

}
