<%@ page import="java.io.IOException" %>
<%@ page import="java.io.FileWriter" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="sample.list.ListOfTasks" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="sample.user.User" %>
<%@ page import="sample.list.UserService" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Your lists:</title>

    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">

</head>
<body>
<div class="container">
<% String login = request.getParameter("login");%>
<h1>Hello, <%=login%> *(^o^)*</h1>
<!--Testing keys and attributes from java code
<//% String testUser = request.getParameter("user");%>
<h1>!!!_<//%=testUser%>_!!! <- If "testValue" is inside, setAttribute works fine</h1>
Testing keys and attributes from java code-->
<form action="/AddListServlet" method="post" >
    <h1></h1>
    <input type="text" name="newTaskListName">
    <input type="submit" name="Add" value="Add">
    <input type="hidden" name="login" value="<%=login%>">
</form>

<h2>Your task lists:</h2>
<% ArrayList<ListOfTasks> listOfTaskLists = new ArrayList<>(); %>
<% UserService userService = UserService.getInstance(); %>

<%
    try {



        for (File file : new File(userService.getCONTEXTPATH() + "/lists/" + login ).listFiles()) {
            String fileName = file.getName();
            fileName = fileName.substring(0, fileName.length() - 4);

            ListOfTasks listOfTasks = new ListOfTasks(fileName, file, new PrintWriter(new FileWriter(file, true)));
            listOfTasks.getPrintWriter().print("");
            listOfTaskLists.add(listOfTasks);
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
%>

<%
    for (int i = 0; i < listOfTaskLists.size(); i++) {
        String name = listOfTaskLists.get(i).getName();
%>
<form action=/ListOfTasksServlet>
    <input type=submit name="nameOfList" value="<%=name%>">
    <input type=hidden name="login" value="<%=login%> ">
</form>
    <%}%>
</div>

</body>
</html>
