<%--
  Created by IntelliJ IDEA.
  User: zc12345
  Date: 2017/5/9
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>hello</title>
</head>
<body>
<div>
    <div>
        <form action="/login" method="post">
            <div id="username">
                <p>账号<input type="text" name="username"></p>
            </div>
            <div id="password">
                <p>密码<input type="password" name="password"></p>
            </div>
            <div id="submit">
                <span><button type="submit" id="login">login</button></span>
            </div>
        </form>
    </div>
</div>
</body>
</html>
