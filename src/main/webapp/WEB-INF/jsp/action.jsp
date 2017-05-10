<%--
  Created by IntelliJ IDEA.
  User: zc12345
  Date: 2017/5/9
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.rbac.bean.Person" %>
<%
    Person person = (Person) session.getAttribute("person");
%>
<html>
<head>
    <title>login</title>
</head>
<body>
<div>
    <p>hello <%=person.getName() %> !</p>
    <p>Now your role is </p>
    <p><%= person.getRole().toString()%>
    </p>
    <p>Now you can click</p>
    <p><%= person.getAuthority().toString()%>
    </p>
</div>
<div>
    <%
        int flag = 2;
        for (String tmp : person.getRole()) {
            switch (tmp) {
                case "admin":
                    flag = 0;
                    break;
                case "user":
                    if (flag > 1) {
                        flag = 1;
                        break;
                    } else break;
                default:
            }
        }
    %>
    <button>按钮0</button>
    <button>按钮1</button>
    <button>按钮2</button>
    <button>按钮3</button>
</div>
</body>
</html>
