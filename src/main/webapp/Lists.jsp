<%@ page import="java.io.IOException" %>
<%@ page import="java.io.FileWriter" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="sample.list.ListOfTasks" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="sample.list.UserService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your lists:</title>
</head>
<body>
<% String login = request.getParameter("login");%>
<h1>Hello, <%=login%> *(^o^)*</h1>

<form action="/AddListServlet" method="post">
    <h1></h1>
    <input type="text" name="newTasknameOfList">
    <input type="submit" name="Add" value="Add new list">
    <input type="hidden" name="login" value="<%=login%>">
</form>

<h2>Your task lists:</h2>
<% ArrayList<ListOfTasks> listOfTaskLists = new ArrayList<>(); %>
<% UserService userService = UserService.getInstance(); %>

<%
    try {
        for (File file : new File(userService.getCONTEXTPATH() + "/lists/" + login).listFiles()) {
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


<form action=/ListOfTasksServlet>
    <%
        for (int i = 0; i < listOfTaskLists.size(); i++) {
            String name = listOfTaskLists.get(i).getName();
    %>
    <table>
        <input type=submit name="nameOfList" value="<%=name%>">
        <input type=hidden name="login" value="<%=login%>">
    </table>
    <br>
    <%}%>
</form>

</body>
</html>