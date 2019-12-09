<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task</title>
</head>

<body>
<% String listName = request.getParameter("nameOfList");%>
<% String login = request.getParameter("login");%>
<h1>List: <%=listName%></h1>

<form action="/AddTaskServlet" method="post">
    <input type="text" name="newTaskName">
    <input type="submit" name="Add" value="Add task">
    <input type="hidden" name="listName" value="<%=listName%>">
    <input type="hidden" name="login" value="<%=login%>">
</form>

<script type="text/javascript">

    //create variables
    let listOfTasksNames = [];
    <%
        List<String> listOfTasksNames = (List<String>) request.getAttribute("listOfTasksNames");
        for (int j = 0; j < listOfTasksNames.size(); j++){
    %>
    listOfTasksNames[<%=j%>] = '<%=listOfTasksNames.get(j)%>';
    <% } %>

    let checkBox = document.createElement("input");
    let textBox = document.createElement("input");
    let par = document.createElement("p");
    let idCheckBox = "CheckBox";
    let idTextBox = "TextBox";


    for (let i = 0; i < listOfTasksNames.length; i++) {

        //update variables
        checkBox = document.createElement("input");
        textBox = document.createElement("input");
        par = document.createElement("p");
        idCheckBox = "CheckBox" + i.toString();
        idTextBox = "TextBox" + i.toString();


        //objects
        checkBox.setAttribute("type", "checkbox");
        checkBox.setAttribute("id", idCheckBox);
        checkBox.setAttribute("onclick", "lineThroughChecked(this," + idTextBox + ")");

        textBox.setAttribute("type", "text");
        textBox.setAttribute("id", idTextBox);
        textBox.setAttribute("value", listOfTasksNames[i])


        //appending
        document.body.appendChild(par);
        document.body.appendChild(checkBox);
        document.body.appendChild(textBox);

        function lineThroughChecked(cb, idTextBox) {
            if (cb.checked) {
                idTextBox.setAttribute("style", "text-decoration: line-through")
            } else {
                idTextBox.setAttribute("style", "text-decoration: none")
            }
        }
    }
</script>

</body>

</html>
