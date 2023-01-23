<%--
  Created by IntelliJ IDEA.
  User: Юлия
  Date: 23.01.2023
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<c:if test="${user != null}">
    <p>Hello, ${user.name}</p>
</c:if>
<h3>Home page. Middle content</h3>
<%--<jsp:include page="footer.jsp"></jsp:include>--%>
</body>
</html>
