package com.packt.rhuan.utils;

import java.security.SecureRandom;
import java.util.Date;

public class TokenUtils {

    public static String generateToken(){

        SecureRandom random = new SecureRandom();
        long longToken = Math.abs( random.nextLong() );
        return Long.toString(new Date().getTime()) + Long.toString( longToken, 16 );

    }
}
