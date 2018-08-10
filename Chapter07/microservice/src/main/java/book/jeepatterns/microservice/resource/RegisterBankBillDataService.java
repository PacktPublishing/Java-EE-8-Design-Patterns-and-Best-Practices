package book.jeepatterns.microservice.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import book.jeepatterns.microservice.vo.RequestVO;
import book.jeepatterns.microservice.vo.ResponseVO;

@Path ("/register")
public class RegisterBankBillDataService {
	@Path ("/paymentSlip/")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response register (RequestVO requestVO) {
		// calls the bank register web service:
		// ...
		// Prepare the response with the register web service data:
		ResponseVO responseVO = new ResponseVO();
		responseVO.setRegisterNumber (7554433452L); // this is a simulation because this information must be returned from the bank's web service 
		responseVO.setUserName (requestVO.getUserName());
		responseVO.setValue (requestVO.getValue());
		responseVO.setStatus ("OK"); // everything is supposed to be correct
		return Response.ok (responseVO).build();
	}
}
