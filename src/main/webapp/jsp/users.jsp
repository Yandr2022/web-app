
<%@ page import="org.example.model.User" %>
<%@ page import="java.util.Set" %>
<%@ page import="org.example.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users List</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
            border: solid;
        }
        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<h2>${msg == null ? '' : msg}</h2>
<table>
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>EMAIL</th>
        <th>PASSWORD</th>
        <th>OFFICE</th>
<%--        TODO ADD HREF VIEW OFFICES--%>
        <th>ROLES</th>
        <th>ACTIVE</th>
        <th>LAST UPDATED</th>
        <th>CREATED</th>
        <th></th>
        <th></th>
<%--        TODO ADD ROLES HEADER HREF--%>
    </tr>
    <c:forEach var="user" items="${users}" varStatus="counter" >
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td><a href="viewOffice?id=${user.office.id}">viewDetails</a></td>
            <td><a href="viewRolesList?userId=${user.id}">viewDetails</a></td>
<%--            <td> SIZE : ${user.roles.size()}> </td>--%>
            <td>${user.isActive}</td>
            <td>${user.updatedTime}</td>
            <td>${user.createdTime}</td>
            <td><a href='users?action=U'>UPDATE</a></td>
            <td><a href='users?action=D'>DELETE</a></td>
        </tr>
    </c:forEach>

<%--    <%--%>
<%--        Set<User> users = (Set<User>) request.getAttribute("users");--%>
<%--        for (User user : users) { %>--%>
<%--    <tr>--%>
<%--        <td><%=user.getId()%></td>--%>
<%--        <td><%=user.getName()%></td>--%>
<%--        <td><%=user.getEmail()%></td>--%>
<%--        <td><%=user.getPassword()%></td>--%>
<%--        <td><%=user.getOffice()%></td>--%>

<%--&lt;%&ndash;         <%for (Role role: user.getRoles()) { %>&ndash;%&gt;--%>
<%--&lt;%&ndash;            <%=role.getName()%>&ndash;%&gt;--%>
<%--&lt;%&ndash;            <% } %>&ndash;%&gt;--%>
<%--        <td><a href="role"> more info here </a> </td>--%>
<%--       <td><%=user.isActive()? "Yes":"No"%></td>--%>
<%--        <td><%=user.getUpdatedTime()%></td>--%>
<%--        <td><%=user.getCreatedTime()%></td>--%>

<%--    </tr>--%>

<%--    <% } %>--%>

</table>
<a href='users?action=C'>CREATE NEW USER</a>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>