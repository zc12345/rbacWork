package com.rbac.servlet;
import com.rbac.bean.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by zc12345 on 2017/5/9.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*super.doPost(req, resp);*/
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*super.doGet(req, resp);*/
        Person person = new Person();
        ArrayList<String> action = new ArrayList<String>();
        ArrayList<String> role = new ArrayList<String>();

        Connection ct = null;
        Statement sm = null;
        ResultSet rs = null;
        try {
            String u=req.getParameter("username");
            String p=req.getParameter("password");
            person.setName(u);
            person.setPassword(p);

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
                    "WHERE username='" + u
                    + "' and password='" + p + "'");
            while(rs.next()){
                System.out.println(rs.getString(1));
                action.add(rs.getString(1));
            }
            rs = sm.executeQuery("SELECT DISTINCT\n" +
                    "    rolename\n" +
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
                    "WHERE username='" + u
                    + "' and password='" + p + "'");
            while (rs.next()){
                System.out.println(rs.getString(1));
                role.add(rs.getString(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            try {
                if (rs!=null) rs.close();
                if (sm!=null) sm.close();
                if (ct!=null) ct.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        person.setAuthority(action);
        person.setRole(role);
        HttpSession session = req.getSession();
        session.setAttribute("person",person);
        req.getRequestDispatcher("/WEB-INF/jsp/action.jsp").forward(req,resp);
    }
}
