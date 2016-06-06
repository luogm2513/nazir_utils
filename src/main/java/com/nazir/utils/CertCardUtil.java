package com.nazir.utils;

import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

/**
 * @Type CertCardUtil
 * @Desc
 * @author luo
 * @date 2016年6月6日
 * @Version V1.0
 */
public class CertCardUtil {

    public static int getSex(String idcardstr) {
        int sexstr = 0;
        // 只正对个人用户的性别进行判断
        // 个人且是身份证
        // 判断用户身份证的长度 15和18位，其余不做考虑
        if (idcardstr != null) {
            idcardstr = idcardstr.trim();
            String tmpjqid = "";
            if (idcardstr.length() == 15) {
                tmpjqid = idcardstr.substring(14, 15);
            }
            if (idcardstr.length() == 18) {
                tmpjqid = idcardstr.substring(16, 17);
            }
            if (!tmpjqid.equals("")) {
                if (Character.isDigit(tmpjqid.charAt(0))) {
                    int intjqid = Integer.parseInt(tmpjqid);
                    if (intjqid % 2 == 0) {
                        sexstr = 2;
                    } else {
                        sexstr = 1;
                    }
                }
            }
        }
        return sexstr;
    }

    public static String getBirthday(String idcardstr) {
        String birthday = null;
        if (StringUtils.isBlank(idcardstr)) {
            return birthday;
        }
        String year = null;
        String month = null;
        String day = null;
        // 解析身份证 获取生日和性别
        if (idcardstr.length() == 15) {
            year = "19" + idcardstr.substring(6, 8);
            month = idcardstr.substring(8, 10);
            day = idcardstr.substring(10, 12);
            if (Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12) {
                month = "01";
            }
            if (Integer.parseInt(day) < 1 || Integer.parseInt(day) > 31) {
                day = "01";
            }
            birthday = year + month + day;
        } else if (idcardstr.length() == 18) {
            year = idcardstr.substring(6, 10);
            month = idcardstr.substring(10, 12);
            day = idcardstr.substring(12, 14);
            if (Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12) {
                month = "01";
            }
            if (Integer.parseInt(day) < 1 || Integer.parseInt(day) > 31) {
                day = "01";
            }
            birthday = year + month + day;
        }
        return birthday;
    }

    /**
     * 输入：身份证号 功能：根据身份证获取年龄 输出：年龄
     * 
     * @param cardno
     * @return
     */
    public static int getAgeBy(String cardno) {
        int age = 0;
        if (StringUtils.isBlank(cardno)) {
            return age;
        }
        int year = 0;
        Calendar CD = Calendar.getInstance();
        if (cardno.length() == 15) {
            // ********************************************身份证为15位
            year = Integer.parseInt(cardno.substring(6, 8));
            age = CD.get(Calendar.YEAR) - 1900 - year;
        } else if (cardno.length() == 18) {
            // ********************************************身份证为18位
            year = Integer.parseInt(cardno.substring(6, 10));
            age = CD.get(Calendar.YEAR) - year;
        }
        return age;
    }

    public static String confusionCardno(String cardno) {
        if (StringUtils.isBlank(cardno)) {
            return null;
        }
        String regex = "(\\w{3})(\\w+)(\\w{4})";
        return cardno.replaceAll(regex, "$1****$3");
    }
}
