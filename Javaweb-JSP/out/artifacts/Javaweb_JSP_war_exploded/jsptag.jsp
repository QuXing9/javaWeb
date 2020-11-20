<%--
  Created by IntelliJ IDEA.
  User: 10592
  Date: 2020/11/10
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--
http://localhost:8080/jsptag.jsp?name=xing&age=12
--%>
<jsp:forward page="/jsptag2.jsp">
    <jsp:param name="name" value="xing"/>
    <jsp:param name="age" value="12"/>
</jsp:forward>
</body>
</html>
