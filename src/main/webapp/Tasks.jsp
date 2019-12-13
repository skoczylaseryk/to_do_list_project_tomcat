<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task</title>
</head>

<body>
<% String nameOfList = request.getParameter("nameOfList");%>
<% String login = request.getParameter("login");%>
<h1>List: <%=nameOfList%></h1>

<form action="/AddTaskServlet" method="post">
    <input type="text" name="newTaskName">
    <input type="submit" name="Add" value="Add task">
    <input type="hidden" name="nameOfList" value="<%=nameOfList%>">
    <input type="hidden" name="login" value="<%=login%>">
</form>

<!-- TODO add method to read if task are done or not (states 'done' and 'todo') -->

<script type="text/javascript">

    //create variables
    let listOfTasksNames = [];
    <%
        List<String> listOfTasksNames = (List<String>) request.getAttribute("listOfTasksNames");
        for (int j = 0; j < listOfTasksNames.size(); j++){
    %>
    listOfTasksNames[<%=j%>] = '<%=listOfTasksNames.get(j)%>';
    <% } %>
    let form = document.createElement("form");
    form.action = "/DeleteTaskServlet";
    form.method = "POST";
    let checkBox = document.createElement("input");
    let textBox = document.createElement("input");
    let deleteTaskButton = document.createElement("input");
    let par = document.createElement("p");
    let idCheckBox = "CheckBox";
    let idTextBox = "TextBox";
    let idDeleteTaskButton = "DeleteTaskButton";


    for (let i = 0; i < listOfTasksNames.length; i++) {

        //update variables
        // form = document.createElement("form")
        checkBox = document.createElement("input");
        textBox = document.createElement("input");
        deleteTaskButton = document.createElement("input");
        par = document.createElement("p");
        idCheckBox = "CheckBox" + i.toString();
        idTextBox = "TextBox" + i.toString();
        idDeleteTaskButton = "deleteTaskButton" + i.toString();


        //objects
        checkBox.type = "checkbox";
        checkBox.id = idCheckBox;
        checkBox.setAttribute("onclick", "lineThroughChecked(this," + idTextBox + ")");

        textBox.type = "text";
        textBox.id = idTextBox;
        textBox.value = listOfTasksNames[i];

        deleteTaskButton.type = "submit";
        deleteTaskButton.id = idDeleteTaskButton;
        deleteTaskButton.value = "Delete";


        //appending
        form.appendChild(par);
        form.appendChild(checkBox);
        form.appendChild(textBox);
        form.appendChild(deleteTaskButton);

        function lineThroughChecked(cb, idTextBox) {
            if (cb.checked) {
                idTextBox.setAttribute("style", "text-decoration: line-through")
            } else {
                idTextBox.setAttribute("style", "text-decoration: none")
            }
        }
    }

    document.body.appendChild(form);
</script>

</body>

</html>
