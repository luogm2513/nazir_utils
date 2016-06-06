package com.nazir.utils;

import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.TypeReference;

/**
 * @Type JsonUtil
 * @Desc 基于JackSon实现的json工具类
 * @author luo
 * @date 2016年5月27日
 * @Version V1.0
 */
public class JsonUtil {

    public static ObjectMapper objectMapper;

    /**
     * 把JavaBean转换为json字符串
     * 
     * @param object
     * @return
     */
    public static String toJSon(Object object) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
     * (1)转换为普通JavaBean：readValue(json,Student.class) (2)转换为List,如List
     * <Student>,将第二个参数传递为Student
     * [].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
     * 
     * @param jsonStr
     * @param valueType
     * @return
     */
    public static <T> T readValue(String jsonStr, Class<T> valueType) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * json数组转List
     * 
     * @param jsonStr
     * @param valueTypeRef
     * @return
     */
    public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        try {
            return objectMapper.readValue(jsonStr, valueTypeRef);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取json中的属性值
     * 
     * @param json
     * @param name
     * @return String
     */
    public static String getStrFromJson(String json, String name) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            Map<String, Object> map = objectMapper.readValue(json, Map.class);
            for (Entry<String, Object> extry : map.entrySet()) {
                if (extry.getKey().equals(name)) {
                    return extry.getValue().toString();
                }
            }
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 对象转换为标准格式输出
     * 
     * @param Object 需要转换的对象
     * @return
     */
    public static String toJSONFormatter(Object obj) {
        if (obj == null) {
            return "";
        }
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
