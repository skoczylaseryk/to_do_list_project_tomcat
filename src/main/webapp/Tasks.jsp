<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task</title>
</head>

<body>

<script type="text/javascript">

    var checkBox = document.createElement("input");
    var textBox = document.createElement("INPUT");
    var par = document.createElement("p");
    var idCheckBox = "CheckBox";
    var idTextBox = "TextBox";


    for (var i = 0; i < 10; i++) {

        //variables
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

</body>
</html>
