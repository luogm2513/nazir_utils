package com.nazir.utils;

import org.apache.commons.logging.Log;

/**
 * @Type LogUtil
 * @Desc
 * @author luo
 * @date 2016年6月6日
 * @Version V1.0
 */
public class LogUtil {

    /**
     * 输出info level的log信息.
     * 
     * @param log 日志记录器
     * @param message log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params log格式化参数,数组length与message参数化个数相同, 如:
     *            <code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void info(Log log, String message, Object... params) {
        if (log.isInfoEnabled()) {
            log.info(format(message, params));
        }
    }

    /**
     * 输出info level的log信息.
     * 
     * @param throwable 异常对象
     * @param log 日志记录器
     * @param message log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params log格式化参数,数组length与message参数化个数相同, 如:
     *            <code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void info(Throwable throwable, Log log, String message, Object... params) {
        if (log.isInfoEnabled()) {
            log.info(format(message, params), throwable);
        }
    }

    /**
     * 输出warn level的log信息.
     * 
     * @param log 日志记录器
     * @param message log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params log格式化参数,数组length与message参数化个数相同, 如:
     *            <code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void warn(Log log, String message, Object... params) {
        if (log.isWarnEnabled()) {
            log.warn(format(message, params));
        }
    }

    /**
     * 输出warn level的log信息.
     * 
     * @param throwable 异常对象
     * @param log 日志记录器
     * @param message log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params log格式化参数,数组length与message参数化个数相同, 如:
     *            <code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void warn(Throwable throwable, Log log, String message, Object... params) {
        if (log.isWarnEnabled()) {
            log.warn(format(message, params), throwable);
        }
    }

    /**
     * 输出debug level的log信息.
     * 
     * @param log 日志记录器
     * @param message log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params log格式化参数,数组length与message参数化个数相同, 如:
     *            <code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void debug(Log log, String message, Object... params) {
        if (log.isDebugEnabled()) {
            log.debug(format(message, params));
        }
    }

    /**
     * 输出debug level的log信息.
     * 
     * @param throwable 异常对象
     * @param log 日志记录器
     * @param message log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params log格式化参数,数组length与message参数化个数相同, 如:
     *            <code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void debug(Throwable throwable, Log log, String message, Object... params) {
        if (log.isDebugEnabled()) {
            log.debug(format(message, params), throwable);
        }
    }

    /**
     * 输出error level的log信息.
     * 
     * @param log 日志记录器
     * @param message log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params log格式化参数,数组length与message参数化个数相同, 如:
     *            <code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void error(Log log, String message, Object... params) {
        if (log.isErrorEnabled()) {
            log.error(format(message, params));
        }
    }

    /**
     * 输出error level的log信息.
     * 
     * @param throwable 异常对象
     * @param log 日志记录器
     * @param message log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params log格式化参数,数组length与message参数化个数相同, 如:
     *            <code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void error(Throwable throwable, Log log, String message, Object... params) {
        if (log.isErrorEnabled()) {
            log.error(format(message, params), throwable);
        }
    }

    /**
     * 日志信息参数化格式化
     * 
     * @param message log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params log格式化参数,数组length与message参数化个数相同, 如:
     *            <code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     * @return 格式化后的日志信息
     */
    private static String format(String message, Object... params) {
        if (params != null && params.length != 0) {
            return MessageUtil.formatMessage(message, params);
        }
        return message;

    }

}
