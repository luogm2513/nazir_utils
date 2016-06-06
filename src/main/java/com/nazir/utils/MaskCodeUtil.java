package com.nazir.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @Type MaskCodeUtil
 * @Desc 打码工具
 * @author luo
 * @date 2016年6月6日
 * @Version V1.0
 */
public class MaskCodeUtil {
    /**
     * 获取打码后字符
     * 
     * @param origChars 原始字符
     * @param type 0 手机号 ，1 身份证
     * @return
     */
    public static String getMaskChars(String origChars, int type) {
        if (StringUtils.isNotBlank(origChars)) {
            if (0 == type) {
                return origChars.substring(0, 3) + "*****" + origChars.substring(8);

            } else if (1 == type) {
                return origChars.substring(0, 3) + "**********" + origChars.substring(13);
            }
            return origChars;
        }
        return "";
    }
}
