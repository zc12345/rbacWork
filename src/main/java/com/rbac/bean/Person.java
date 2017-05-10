package com.rbac.bean;

import java.util.ArrayList;

/**
 * Created by zc12345 on 2017/5/9.
 */
public class Person {
    private String name;
    private String password;
    private ArrayList<String> role;
    private ArrayList<String> authority;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getAuthority() {
        return authority;
    }

    public void setAuthority(ArrayList<String> authority) {
        this.authority = authority;
    }

    public ArrayList<String> getRole() {
        return role;
    }

    public void setRole(ArrayList<String> role) {
        this.role = role;
    }
}
