
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>


<!-- #1 Declaration  == Java field-->
  <%!Date serverDate = new Date();%>

  <!-- #2 Scriptlet-->
<%
  //start Java logic
  System.out.println("Java Scriptlet" + serverDate);
  for (int i = 0; i < ((int)(Math.random()*10)+1); i++) { %>
        <h4> <%=i%></h4>
  <% }%>

<!-- #3 Expression-->
<h2> <%=serverDate%></h2>

</body>
</html>