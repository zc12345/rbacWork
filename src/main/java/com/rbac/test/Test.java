package com.rbac.test;

import com.rbac.bean.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by zc12345 on 2017/5/9.
 */
public class Test {
    public static void main(String[] args) {

        Connection ct = null;
        Statement sm = null;
        ResultSet rs = null;
        try {
            String u = "why";
            String p = "111111";

            //数据库连接
            Class.forName("com.mysql.jdbc.Driver");
            //得到连接
            ct = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/rbac", "root", "8080");
            //创建Statement
            sm = ct.createStatement();
            rs = sm.executeQuery("SELECT DISTINCT\n" +
                    "    action\n" +
                    "FROM\n" +
                    "    rbac.authority\n" +
                    "        NATURAL JOIN\n" +
                    "    rbac.`role-auth`\n" +
                    "        NATURAL JOIN\n" +
                    "    rbac.role\n" +
                    "        NATURAL JOIN\n" +
                    "    rbac.`role-user`\n" +
                    "        NATURAL JOIN\n" +
                    "    rbac.user\n" +
                    "WHERE\n" +
                    "    username = 'Bob' AND password = '123456';");
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
