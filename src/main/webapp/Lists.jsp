<%@ page import="java.io.File" %>
<%@ page import="sample.user.User" %>
<%@ page import="sample.services.impl.UserServiceImpl" %>
<%@ page import="java.util.*" %>
<%@ page import="sample.FileComparator" %>

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
    <%User user = (User) session.getAttribute("user");%>
    <% String login = user.getLogin();%>
    <% String newListNumber = String.valueOf(request.getAttribute("newListNumber"));%>

    <form action="/LogOutServlet" method="post">
        <h2>Hello, <%=login%>
            <input type="submit" id="Logout" name="Logout" value="Log out"></h2>
        <input type="hidden" name="logout" value="logout">
    </form>


    <h2>Your task lists:</h2>


    <form action="/AddListServlet" method="post">
        <input type="text" name="newTaskListName" placeholder="NewList<%=newListNumber%>">
        <input type="submit" name="Add" value="Add">
        <input type="hidden" name="login" value="<%=login%>">
    </form>

    <% UserServiceImpl userServiceImpl = UserServiceImpl.getInstance(); %>

    <%

        File[] files = new File(userServiceImpl.getCONTEXTPATH() + "/lists/" + login).listFiles();
        Arrays.sort(files, new FileComparator());

    %>
    <%
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getName();
            System.out.println(fileName);
            String name = fileName.substring(0, fileName.length() - 4);
    %>
    <table>
        <tr>
            <form action=/ListOfTasksServlet method="post">
                <input type=submit name="nameOfList" value="<%=name%>">
                <input type=hidden name="login" value="<%=login%>">
            </form>
            <form action="/DeleteListServlet" method="post">
                <input type="submit" name="deleteList" value="Delete">
                <input type="hidden" name="nameOfList" value="<%=name%>">
            </form>
        </tr>
    </table>
    <%}%>

</div>

</body>
</html>