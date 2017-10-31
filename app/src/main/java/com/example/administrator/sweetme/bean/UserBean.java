package com.example.administrator.sweetme.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by FancyMenG on 2017/10/8.
 */

public class UserBean extends BmobObject {
    private String name;
    private String passWord;
    private String phone;
    private String roleKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }
}
