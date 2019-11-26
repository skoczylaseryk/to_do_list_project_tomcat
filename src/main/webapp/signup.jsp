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
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>signup.jsp</title>

    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">

</head>
<body>
<div class="container">

    <h2 id="header">Input login and password</h2>

<form action="SignUpServlet">
    <input type="text" id="signuplogin" name="login" placeholder="login"><br>
    <input type="password" id="signuppassword" name="password" placeholder="password"><br>
    <input type="submit" name="sign up" value="sign up">

</form>
</div>

</body>
</html>
