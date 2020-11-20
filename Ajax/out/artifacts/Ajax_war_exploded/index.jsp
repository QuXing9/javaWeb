<%--
  Created by IntelliJ IDEA.
  User: 10592
  Date: 2020/11/16
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  <%--    要使用jQuery,需要导入--%>
    <script src="${pageContext.request.contextPath}/statics/js/jquery-3.5.1.js"></script>

    <script type="text/javascript">
      function a1(){
        $.ajax({
          url: "${pageContext.request.contextPath}/ajax/a1",
          data:{"name":$("txtName").val()},
          success: function (data, status){
            alert(data);
            alert(status);
          }
        });
      }
    </script>

  </head>
  <body>

  用户名:<input type="text" id="txtName" onblur="a1()"/>


  </body>
</html>
