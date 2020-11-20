<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 10592
  Date: 2020/11/10
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    ArrayList<String> people = new ArrayList<>();
    people.add(0, "张三");
    people.add(1, "李四");
    people.add(2, "王五");
    people.add(3, "赵六");
    people.add(4, "田七");
    request.setAttribute("list", people);
%>

<%--
var , 每一次遍历出来的变量
items, 要遍历的对象
begin, 哪里开始
end, 结束
step, 步长
--%>
<c:forEach var="people" items="${list}">
    <c:out value="${people}" /> <br>
</c:forEach>

<hr>

<c:forEach var="people" items="${list}" begin="1" end="3" step="2">
    <c:out value="${people}" /> <br>
</c:forEach>

</body>
</html>
