package com.mca.shardingsphere.bean;

/**
 * ClassName: User
 * Package: com.mca.shardingsphere.bean
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/10 - 20:43
 * @version: v1.0
 */
public class User {
    private Integer userId;
    private String pwd;
    private String userName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
