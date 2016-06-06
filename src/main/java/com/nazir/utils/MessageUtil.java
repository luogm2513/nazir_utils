package com.nazir.utils;

import java.text.MessageFormat;

/**
 * @Type MessageUtil
 * @Desc
 * @author luo
 * @date 2016年6月6日
 * @Version V1.0
 */
public class MessageUtil {

    /**
     * 使用MessageFormat格式化字符串. <br>
     * Parameters: message 要格式化的字符串 params 参数表 <br>
     * Returns: 格式化的字符串，如果message为null，则返回null
     */
    public static String formatMessage(String message, Object... params) {
        if (message == null || params == null || params.length == 0) {
            return message;
        }

        return MessageFormat.format(message, params);
    }

    public static void main(String[] args) {
        String showMsg = MessageUtil.formatMessage("这是一个{0}工具类，用于对{1}进行拼装！", "Message", "Message");
        System.out.println(showMsg);
    }

}
