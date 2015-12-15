package com.himeetu.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 class.Collected from the Internet
 *
 * @copyright (C) GT 2009 All Rights Reserved
 * <p/>
 * $Revision: 1.1 $ $Date: 2009/05/13 16:41:00 $
 */
public class MD5 {
    private static char hexDigits[] = {       // 用来将字节转换成 16 进制表示的字符  
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String MD5(String source) {
        MessageDigest md5 = null;

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        byte[] bs = null;

        try {
            bs = md5.digest(source.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        char str[] = new char[32];
        int k = 0;                                // 表示转换结果中对应的字符位置
        for (int i = 0; i < 16; i++) {          // 从第一个字节开始，对 MD5 的每一个字节  
            // 转换成 16 进制字符的转换  
            byte byte0 = bs[i];                 // 取第 i 个字节  
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];  // 取字节中高 4 位的数字转换,  
            // >>> 为逻辑右移，将符号位一起右移  
            str[k++] = hexDigits[byte0 & 0xf];            // 取字节中低 4 位的数字转换  
        }
        return new String(str);
    }
}
