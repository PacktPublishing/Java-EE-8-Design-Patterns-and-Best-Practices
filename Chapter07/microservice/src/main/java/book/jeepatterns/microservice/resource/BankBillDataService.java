package book.jeepatterns.microservice.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import book.jeepatterns.microservice.model.User;
import book.jeepatterns.microservice.service.UserService;
import book.jeepatterns.microservice.vo.RequestVO;
import book.jeepatterns.microservice.vo.ResponseVO;

/**
 * This is the aggregator service class
 * @author João Carlos
 *
 */
@Path ("/bank")
public class BankBillDataService {
	
	private static String BASE_PATH = "http://localhost:8080/microservice/ws";
	
	@Inject
	private UserService userService;
	
	private static double convertToValue (String strValue) {
		strValue = strValue.trim();
		strValue = strValue.substring (0, strValue.length() - 2) + "." + strValue.substring (strValue.length() - 2);
		return Double.parseDouble (strValue);
	}
	
	private Response callMicroservice (String path, Object entity) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target (BASE_PATH);
		Response resp = target.path (path).request().post(Entity.json (entity));
		return resp;
	}
	
	@Path ("/paymentSlip/{id}/{value}")
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public Response getPaymentSlipData (@PathParam("id") long id, @PathParam("value") String strValue) {
		
		// get the payment slip value:
		double value = convertToValue (strValue);
		
		// get the user information from local DataBase:
		User user = userService.getUserById (id);

		// prepare the payment slip registering request:
		RequestVO registerRequest = new RequestVO();
		registerRequest.setUserCode (user.getCode());
		registerRequest.setUserName (user.getName());
		registerRequest.setValue (value);
		// call registering microservice:
		Response resp = callMicroservice ("/register/paymentSlip", registerRequest);
		ResponseVO responseRegisterVO  = resp.readEntity (ResponseVO.class); 
		
		// prepare the payment slip query request:
		RequestVO queryRequest = new RequestVO();
		queryRequest.setUserName(registerRequest.getUserName());
		queryRequest.setRegisterNumber (responseRegisterVO.getRegisterNumber());
		queryRequest.setValue (value);
		// call query microservice:
		resp = callMicroservice ("/query/paymentSlip", registerRequest);
		ResponseVO responseQueryVO  = resp.readEntity (ResponseVO.class); 
		
		return Response.ok (responseQueryVO).build();
	}
	

}
