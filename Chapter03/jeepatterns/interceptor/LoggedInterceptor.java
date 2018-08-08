package book.jeepatterns.interceptor;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Logged
@Interceptor
public class LoggedInterceptor implements Serializable {
	
	@AroundInvoke
	public Object logMethod (InvocationContext invocationContext) throws Exception{
		System.out.println("Entering method : "
				+ invocationContext.getMethod().getName() + " " 
				+ invocationContext.getMethod().getDeclaringClass()
				);
		Object o =  invocationContext.proceed();
		return o;
	}
	
}
