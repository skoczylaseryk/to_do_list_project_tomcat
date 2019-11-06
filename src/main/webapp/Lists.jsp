<%@ page import="java.io.IOException" %>
<%@ page import="java.io.FileWriter" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="sample.list.ListOfTasks" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your lists:</title>
</head>
<body>
<% String login = request.getParameter("login");%>
<h1>Hello, <%=login%> *(^o^)*</h1>

<!--Testing keys and attributes from java code
<//% String testUser = request.getParameter("user");%>
<h1>!!!_<//%=testUser%>_!!! <- If "testValue" is inside, setAttribute works fine</h1>
Testing keys and attributes from java code-->
<h2>Your task lists:</h2>

<%ArrayList<ListOfTasks> listOfTaskLists = new ArrayList<>();%>

<%
    try {
        for (File file : new File(request.getSession().getServletContext().getRealPath(".\\lists")).listFiles()) {
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
<form action=/LoggedInServlet>
    <input type=submit name="nameOfList" value="<%=name%>">
</form>

<%}%>
</body>
</html>
