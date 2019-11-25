<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task</title>
</head>

<body>

<script type="text/javascript">
    //Creating list of tasks, available from user's task list
    //variables
    var checkBox = document.createElement("input");
    var textBox = document.createElement("input");
    var par = document.createElement("p");
    var idCheckBox = "CheckBox";
    var idTextBox = "TextBox";


    for (var i = 0; i < 10; i++) {

        //update variables
        checkBox = document.createElement("input");
        textBox = document.createElement("INPUT");
        par = document.createElement("p");
        idCheckBox = "CheckBox" + i.toString();
        idTextBox = "TextBox" + i.toString();


        //objects
        checkBox.setAttribute("type", "checkbox");
        checkBox.setAttribute("id", idCheckBox);
        checkBox.setAttribute("onclick", "lineThroughChecked(this," + idTextBox + ")");

        textBox.setAttribute("type", "text");
        textBox.setAttribute("id", idTextBox);


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

<script type="text/javascript">
    var checkBoxX = document.createElement("input");
    var textBoxX = document.createElement("INPUT");
    var parX = document.createElement("p");
    var idCheckBoxX = "CheckBoxX";
    var idTextBoxX = "TextBoxX";

    function createLine() {
        checkBoxX = document.createElement("input");
        textBoxX = document.createElement("INPUT");
        parX = document.createElement("p");
        idCheckBoxX = "CheckBoxX";
        idTextBoxX = "TextBoxX";


        //objects
        checkBoxX.setAttribute("type", "checkbox");
        checkBoxX.setAttribute("id", idCheckBoxX);
        checkBoxX.setAttribute("onclick", "lineThroughChecked1(this," + idTextBoxX + ")");

        textBoxX.setAttribute("type", "text");
        textBoxX.setAttribute("id", idTextBoxX);


        //appending
        document.body.appendChild(parX);
        document.body.appendChild(checkBoxX);
        document.body.appendChild(textBoxX);
    }
    function lineThroughChecked1(cb, idTextBoxX) {
        if (cb.checked) {
            idTextBoxX.setAttribute("style", "text-decoration: line-through")
        } else {
            idTextBoxX.setAttribute("style", "text-decoration: none")
        }
    }
</script>

</body>
<footer>
    <form>
        <input type="button" name="Add" value="Add" onclick="createLine()">
    </form>
</footer>
</html>
