<%--
  Created by IntelliJ IDEA.
  User: 10592
  Date: 2020/11/11
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h1>当前有
    <span>
    <%=this.getServletConfig().getServletContext().getAttribute("OnlineCount")%>
    </span>人在线
  </h1>
  </body>
</html>
