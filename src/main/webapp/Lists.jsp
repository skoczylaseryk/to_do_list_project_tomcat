<%@ page import="java.io.IOException" %>
<%@ page import="java.io.FileWriter" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="sample.list.ListOfTasks" %>
<%@ page import="java.io.File" %>
<%@ page import="sample.user.User" %>
<%@ page import="sample.services.UserService" %>
<%@ page import="java.nio.file.attribute.BasicFileAttributes" %>
<%@ page import="java.nio.file.Files" %>
<%@ page import="java.nio.file.Paths" %>
<%@ page import="java.nio.file.attribute.FileTime" %>
<%@ page import="com.google.common.collect.Lists" %>
<%@ page import="java.util.*" %>
<%@ page import="sample.FileComparator" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Your lists:</title>

    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">

</head>
<body>
<div class="container">
    <% String login = request.getParameter("login");%>
    <% String newListNumber = String.valueOf(request.getAttribute("newListNumber"));%>
    <h1>Hello, <%=login%> *(^o^)*</h1>

    <form action="/AddListServlet" method="post">
        <input type="text" name="newTaskListName" placeholder="NewList<%=newListNumber%>">
        <input type="submit" name="Add" value="Add">
        <input type="hidden" name="login" value="<%=login%>">
    </form>

    <h2>Your task lists:</h2>
    <% UserService userService = UserService.getInstance(); %>

    <%

            File[] files = new File(userService.getCONTEXTPATH() + "/lists/" + login).listFiles();
            Arrays.sort(files, new FileComparator());
            System.out.println(Arrays.toString(files));


            for (int i = 0; i < files.length; i++) {


                String fileName = files[i].getName();
                System.out.println(fileName);
                String name = fileName.substring(0, fileName.length() - 4);

                %>

 <form action=/ListOfTasksServlet>
        <input type=submit name="nameOfList" value="<%=name%>">
    <input type=hidden name="login" value="<%=login%> ">
    </form>

            <%}%>
</div>

</body>
</html>
