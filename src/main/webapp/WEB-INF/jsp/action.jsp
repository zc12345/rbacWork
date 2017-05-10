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
<%@ page import="java.util.ArrayList" %>
<%
    Person person = (Person) session.getAttribute("person");
    int flag = 0;
    ArrayList<String> arrayList = new ArrayList<String >();
    for (String s : person.getAuthority()
         ) {
        flag++;
        arrayList.add(s);
    }
    if (flag<=2){
        arrayList.add("btn");
        arrayList.add("btn");
    }
    person.setAuthority(arrayList);
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
    <button id="<%=person.getAuthority().get(0)%>">按钮0</button>
    <button id="<%=person.getAuthority().get(1)%>">按钮1</button>
    <button id="<%=person.getAuthority().get(2)%>">按钮2</button>
    <button id="<%=person.getAuthority().get(3)%>">按钮3</button>
    <script type="text/javascript">
        var btn0 = document.getElementById("button0");
        var btn1 = document.getElementById("button1");
        var btn2 = document.getElementById("button2");
        var btn3 = document.getElementById("button3");
        var btn = document.getElementById("btn");
        btn0.onclick = function() {
            alert("button0!");
        };
        btn1.onclick = function() {
            alert("button1!");
        };
        btn2.onclick = function() {
            alert("button2!");
        };
        btn3.onclick = function() {
            alert("button3!");
        };
        btn.onclick = function () {
            alert("你没有权限！");
        };

    </script>
</div>
</body>
</html>
