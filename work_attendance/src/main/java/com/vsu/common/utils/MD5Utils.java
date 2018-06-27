package com.vsu.common.utils;

import sun.misc.BASE64Encoder;
import sun.plugin2.message.Message;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by vsu on 2018/03/15.
 */
public class MD5Utils {


    public static boolean checkPassword(String inputPwd, String dbPwd) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String result = encrptyPassword(inputPwd);
        if (result.equals(dbPwd)){
            return true;
        }else {
            return false;
        }
    }

    public static String encrptyPassword(String inputPwd) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md5 =  MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String result= base64Encoder.encode(md5.digest(inputPwd.getBytes("utf-8")));
        return result;
    }
}
