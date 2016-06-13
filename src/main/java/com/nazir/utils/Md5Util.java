package com.nazir.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Type Md5Util
 * @Desc
 * @author luo
 * @date 2016年6月13日
 * @Version V1.0
 */
public class Md5Util {

    /**
     * 编码格式
     */
    private static String encoding = "utf-8";

    public static String getMD5String(String value) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return getMD5String(value.getBytes(encoding));
    }

    public static String getMD5String(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("md5");
        byte[] e = md.digest(bytes);
        return toHexString(e);
    }

    private static String toHexString(byte bytes[]) {
        StringBuilder hs = new StringBuilder();
        String stmp = "";
        for (int n = 0; n < bytes.length; n++) {
            stmp = Integer.toHexString(bytes[n] & 0xff);
            if (stmp.length() == 1)
                hs.append("0").append(stmp);
            else
                hs.append(stmp);
        }
        return hs.toString();
    }

}
