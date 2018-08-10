package com.packt.rhuan.utils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Base64;

public class AuthUtils {

    public static final int INDEX_LOGIN = 0;
    public static final int INDEX_PASSWORD = 1;

    public static String[] readBasicAuthHeader( HttpServletRequest request ){

        final String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Basic")) {

            String base64Credentials = authorization.substring("Basic".length()).trim();
            String credentials =
                    new String(
                            Base64.getDecoder().decode(
                                    base64Credentials),
                            Charset.forName("UTF-8"));

            return credentials.split(":", 2);
        }

        return new String[0];
    }
}
