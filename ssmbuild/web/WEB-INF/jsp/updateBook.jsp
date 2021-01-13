<%--
  Created by IntelliJ IDEA.
  User: 10592
  Date: 2021/1/11
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改书籍</title>
    <%--BootStrap美化界面--%>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="page-header">
                    <h1>
                        <small>修改书籍</small>
                    </h1>
                </div>
            </div>
        </div>

        <form action="${pageContext.request.contextPath}/book/updateBook" method="post">
            <input type="hidden" name="bookID" value="${QBook.bookID}">
            <div class="form-group">
                <label>书籍名称：</label>
                <input type="text" name="bookName" class="form-control" value="${QBook.bookName}" required>
            </div>
            <div class="form-group">
                <label>书籍数量：</label>
                <input type="text" name="bookCounts" class="form-control" value="${QBook.bookCounts}" required>
            </div>
            <div class="form-group">
                <label>书籍描述：</label>
                <input type="text" name="detail" class="form-control" value="${QBook.detail}" required>
            </div>
            <div class="form-group">
                <input type="submit" class="form-control" value="修改">
            </div>
        </form>
    </div>
</body>
</html>
