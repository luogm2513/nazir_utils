package com.nazir.nazir_utils.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.nazir.object.DeptBean;
import com.nazir.object.UserBean;
import com.nazir.utils.JsonUtil;

/**
 * @Type JsonUtilTest
 * @Desc
 * @author luo
 * @date 2016年5月27日
 * @Version V1.0
 */
public class JsonUtilTest {
    public static void main(String[] args) {

        UserBean userBean1 = new UserBean(1, "liubei", "123", "liubei@163.com");
        UserBean userBean2 = new UserBean(2, "guanyu", "123", "guanyu@163.com");
        UserBean userBean3 = new UserBean(3, "zhangfei", "123", "zhangfei@163.com");

        List<UserBean> userBeans = new ArrayList<UserBean>();
        userBeans.add(userBean1);
        userBeans.add(userBean2);
        userBeans.add(userBean3);

        DeptBean deptBean = new DeptBean(1, "sanguo", userBeans);
        // 对象转json
        String userBeanToJson = JsonUtil.toJSon(userBean1);
        String deptBeanToJson = JsonUtil.toJSon(deptBean);

        // System.out.println("deptBean to json:" + deptBeanToJson);
        // System.out.println("userBean to json:" + userBeanToJson);

        // json转字符串
        UserBean jsonToUserBean = JsonUtil.readValue(userBeanToJson, UserBean.class);
        DeptBean jsonToDeptBean = JsonUtil.readValue(deptBeanToJson, DeptBean.class);

        // System.out.println("json to DeptBean" + jsonToDeptBean.toString());
        // System.out.println("json to UserBean" + jsonToUserBean.toString());

        // List 转json字符串
        String listToJson = JsonUtil.toJSon(userBeans);
        // System.out.println("list to json:" + listToJson);

        // 数组json转 List
        List<UserBean> jsonToUserBeans = JsonUtil.readValue(listToJson, new TypeReference<List<UserBean>>() {
        });
        String userBeanString = "";
        for (UserBean userBean : jsonToUserBeans) {
            userBeanString += userBean.toString() + "\n";
        }
        // System.out.println("json to userBeans:" + userBeanString);

        String json = "{\"deptId\":1,\"deptName\":\"sanguo\",\"userBeanList\":[{\"userId\":1,\"userName\":\"liubei\",\"password\":\"123\",\"email\":\"liubei@163.com\"},{\"userId\":2,\"userName\":\"guanyu\",\"password\":\"123\",\"email\":\"guanyu@163.com\"}]}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode node = objectMapper.readTree(json);
            String deptName = node.get("deptName").getTextValue();
            JsonNode userBeanListNode = node.get("userBeanList");
            List<UserBean> list = objectMapper.readValue(userBeanListNode, new TypeReference<List<UserBean>>() {
            });
            System.out.println(JsonUtil.toJSon(list));
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
