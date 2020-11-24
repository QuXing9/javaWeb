<%--
  Created by IntelliJ IDEA.
  User: 10592
  Date: 2020/11/23
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
<%--  通过表单上传文件--%>
<%--${pageContext.request.contextPath}获取服务器路径--%>
  <form action="${pageContext.request.contextPath}/upload.do" enctype="multipart/form-data" method="post">
    上传用户:<input type="text" name="username"><br/>
    <p><input type="file" name="file1"></p>
    <p><input type="submit"> | <input type="reset"></p>
  </form>
  </body>
</html>
