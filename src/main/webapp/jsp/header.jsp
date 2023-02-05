<%@ page import="org.example.model.User" %>
<%@ page import="org.example.util.ServletUtils" %><%--
  Created by IntelliJ IDEA.
  User: Юлия
  Date: 23.01.2023
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<style>
  ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #333;
  }

  li {
    float: left;
  }

  li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
  }

  li a:hover:not(.active) {
    background-color: #111;
  }

  .active {
    background-color: #04AA6D;
  }
</style>
<ul>
  <li><a href="home">Home</a></li>
  <li><a href="offices">Offices</a></li>
  <li><a href="users">Users</a></li>
  <%
    User user = ServletUtils.getSessionUser(request);
    if (user == null) { %>
  <li style="float:right"><a class="active" href="login">LOGIN</a></li>
  <% } else { %>
  <li style="float:right">Hello, <%= user.getName() %></li>
  <li style="float:right"><a class="active" href="logout">LOG OUT</a></li>

  <%}%>


</ul>


<%--<div style="padding-left:16px">--%>
<%--  <h2>Top Navigation Example</h2>--%>
<%--  <p>Some content..</p>--%>
<%--</div>--%>



