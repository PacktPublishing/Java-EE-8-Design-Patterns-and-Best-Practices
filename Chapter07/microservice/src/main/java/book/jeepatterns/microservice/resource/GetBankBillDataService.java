package book.jeepatterns.microservice.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import book.jeepatterns.microservice.vo.RequestVO;
import book.jeepatterns.microservice.vo.ResponseVO;

@Path ("/query")
public class GetBankBillDataService {
	@Path ("/paymentSlip/")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response query (RequestVO requestVO) {
		// calls the bank query web service:
		// ...
		// Prepare the response with the query web service data:
		ResponseVO responseVO = new ResponseVO();
		responseVO.setCodeBar("8888999977776666");// this is a simulation because this information must be returned from the bank's web service 
		responseVO.setUserName (requestVO.getUserName());
		responseVO.setStatus ("OK"); // everything is supposed to be correct
		responseVO.setValue(requestVO.getValue());
		return Response.ok (responseVO).build();
	}
}
