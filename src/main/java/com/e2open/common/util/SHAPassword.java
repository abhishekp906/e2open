package com.e2open.common.util;

import java.security.MessageDigest;

/**
 * Created by abhishek on 5/19/16.
 */
public class SHAPassword {

    private static MessageDigest digest;

    public static String sha256(String password) {
        try{
            if (digest == null)
                digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
