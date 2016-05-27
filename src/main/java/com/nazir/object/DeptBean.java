package com.nazir.object;

import java.util.List;

public class DeptBean {
    private int deptId;
    private String deptName;
    private List<UserBean> userBeanList;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<UserBean> getUserBeanList() {
        return userBeanList;
    }

    public void setUserBeanList(List<UserBean> userBeanList) {
        this.userBeanList = userBeanList;
    }

    @Override
    public String toString() {
        String userBeanListString = "";
        for (UserBean userBean : userBeanList) {
            userBeanListString += userBean.toString() + "\n";
        }

        return "DeptBean [deptId=" + deptId + ", deptName=" + deptName + ", \nuserBeanListString=" + userBeanListString
                + "]";
    }

    public DeptBean(int deptId, String deptName, List<UserBean> userBeanList) {
        super();
        this.deptId = deptId;
        this.deptName = deptName;
        this.userBeanList = userBeanList;
    }

    public DeptBean() {
        super();
    }
}
