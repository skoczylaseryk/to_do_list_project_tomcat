<%@ page import="sample.list.UserService" %><%--
  Created by IntelliJ IDEA.
  User: Eryk
  Date: 19.11.2019
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>signup.jsp</title>
</head>
<body>

<form action="SignUpServlet">
    <input type="text" name="login" placeholder="login">
    <input type="password" name="password" placeholder="password">
    <input type="submit" name="sign up" value="sign up">
</form>


</body>
</html>
