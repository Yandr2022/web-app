<%@ page import="java.sql.Connection" %>
<%@ page import="org.example.util.DB_Util" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="org.example.model.User" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Юлия
  Date: 18.01.2023
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JDBC</title>
</head>
<body>
<% Connection connection = DB_Util.getConnection();
  String sql = "SELECT * FROM offices WHERE id = 1";
  Statement statement = null;
  ResultSet set = null;
  try {
    statement = connection.createStatement();
    set = statement.executeQuery(sql);
    if (set.next()) {
    %>
<h4><%=set.getInt(1)%></h4>
<h4><%=set.getString(2)%></h4>
<h4><%=set.getString(3)%></h4>
   <% }
  } catch (SQLException e) {
    throw new RuntimeException(e);
  } finally {
    DB_Util.release(connection, statement, null, set);
  }%>

  </body>
</html>
