package com.nazir.utils;

import java.util.Random;

/**
 * @Type RandomUtil
 * @Desc
 * @author luo
 * @date 2016年6月6日
 * @Version V1.0
 */
public class RandomUtil {

    private final static String LOWERS = "abcdefghijklmnopqrstuvwxyz"; // 小写字母
    private final static String LOWER_DIGITS = "abcdefghijklmnopqrstuvwxyz0123456789"; // 小写字母和数字

    /**
     * 获取随机小写字母字符串
     * 
     * @param n
     * @return
     */
    public static String randLowers(int n) {
        return getRandStr(n, LOWERS);
    }

    /**
     * 获取随机小写和数字字符串
     * 
     * @param n
     * @return
     */
    public static String randLowerDigits(int n) {
        return getRandStr(n, LOWER_DIGITS);
    }

    /**
     * 获取随机字符串
     * 
     * @param n
     * @param str
     * @return
     */
    private static String getRandStr(int n, String str) {
        StringBuilder s = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            s.append(str.charAt(random.nextInt(str.length())));
        }
        return s.toString();
    }

}
