package com.packt.rhuan.interceptors;


import com.packt.rhuan.auth.Auth;
import com.packt.rhuan.utils.AuthUtils;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

@Authentication
@Interceptor
public class AuthenticationInterceptor implements Serializable{

    @Inject
    private Auth auth;

    @AroundInvoke
    public Object authentication(InvocationContext context) throws IOException {

        HttpServletRequest request = getHttpServletRequest( context );
        HttpServletResponse response = getHttpServletResponse( context );

        String[] credentials = AuthUtils.readBasicAuthHeader( request );

        String login;
        String password;

        if( credentials.length < 2
                || !auth.isAuthenticated( login = credentials[AuthUtils.INDEX_LOGIN] , password = credentials[AuthUtils.INDEX_PASSWORD] ) ){
            prepareDialogBox( response );
            return null;

        }

        Authentication authentication = context.getMethod().getAnnotation( Authentication.class );

        if ( Arrays.stream(authentication.roles()).noneMatch( role -> auth.isAuthorized( login, role )) ){

            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }

        try {
            return context.proceed();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        return null;

    }

    public void prepareDialogBox( HttpServletResponse response ) throws IOException {
        response.addHeader("WWW-Authenticate", "Basic");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    private HttpServletRequest getHttpServletRequest(InvocationContext ic) {

        return (HttpServletRequest) Arrays
                .stream(ic.getParameters()).filter(p ->  p instanceof HttpServletRequest )
                .findFirst()
                .get();

    }

    private HttpServletResponse getHttpServletResponse(InvocationContext ic) {

        return (HttpServletResponse) Arrays
                .stream(ic.getParameters()).filter(p ->  p instanceof HttpServletResponse )
                .findFirst()
                .get();


    }

}
