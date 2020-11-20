<%--
  Created by IntelliJ IDEA.
  User: 10592
  Date: 2020/11/9
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--内置对象--%>
<%
    pageContext.setAttribute("name1","星星一号");
    request.setAttribute("name2", "星星二号");
    session.setAttribute("name3","星星三号");
    application.setAttribute("name4","星星四号");
%>
<%--脚本片段中的代码,会被原封不动生成到JSP.java中
要求:这里面的代码,必须保证Java语法的正确性
--%>
<%
    //从pageContext中取出,我们通过寻找的方式来
    // 从底层到高层(作用域):
    String name1 = (String) pageContext.findAttribute("name1");
    String name2 = (String) pageContext.findAttribute("name2");
    String name3 = (String) pageContext.findAttribute("name3");
    String name4 = (String) pageContext.findAttribute("name4");
    String name5 = (String) pageContext.findAttribute("name5");
%>

<%--使用EL表达式输出 ${}--%>
<h1>取出的值为:</h1>
<h3>${name1}</h3>
<h3>${name2}</h3>
<h3>${name3}</h3>
<h3>${name4}</h3>
<h3>${name5}</h3>

</body>
</html>
