<%@ page import="com.xing.pojo.People" %><%--
  Created by IntelliJ IDEA.
  User: 10592
  Date: 2020/11/11
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
//    People people = new People();
//    people.setAddress();
//    people.setId();
//    people.setAge();
//    people.setName();
%>
<jsp:useBean id="people" class="com.xing.pojo.People" scope="page" />
<jsp:setProperty name="people" property="address" value="xian" />
<jsp:setProperty name="people" property="age" value="20" />
<jsp:setProperty name="people" property="name" value="xingxing" />
<jsp:setProperty name="people" property="id" value="1" />

姓名：<jsp:getProperty name="people" property="name"/>
ID：<jsp:getProperty name="people" property="id"/>
年龄：<jsp:getProperty name="people" property="age"/>
地址：<jsp:getProperty name="people" property="address"/>

</body>
</html>
