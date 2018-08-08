package book.jeepatterns.interceptor;

import java.io.Serializable;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import book.jeepatterns.model.TestRevisionTO;

@Statistical
@Interceptor
public class Statisticalnterceptor implements Serializable {
	
	@Inject                                    
	Event<String> event;	
	
	@AroundInvoke
	public Object statisticMethod (InvocationContext invocationContext) throws Exception{
		System.out.println("Statistical method : "
				+ invocationContext.getMethod().getName() + " " 
				+ invocationContext.getMethod().getDeclaringClass()
				);
		
		// get the enrollment:
		TestRevisionTO testRevisionTO = (TestRevisionTO)invocationContext.getParameters()[0];
		
		System.out.println("Enrolment : " + testRevisionTO.getEnrollment());
		
		// event.fireAsync (testRevisionTO.getEnrollment());// fire an async statistical event. This is new in JEE8.
		event.fire (testRevisionTO.getEnrollment());
		
		Object o =  invocationContext.proceed();
		return o;
	}
	
}
