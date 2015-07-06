<%--
  Created by IntellkDEA.
  User: AppleClub
  Date: 15/6/3
  Time: 下午8:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Result</title>
</head>
<body>
<h1>Ur Key Word is ${requestScope.keyWord}</h1>
<br>
<c:forEach items="${requestScope.result}" var="item" begin="0" end="9" step="1">
  <%--<c:if test="${status.count <= 10}">--%>
    <h3>${item.title}</h3>
    <p>${item.content}</p>
    <p>${item.url}</p>
    <br>
    <br>
  <%--</c:if>--%>
</c:forEach>
</body>
</html>
