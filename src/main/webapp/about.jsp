<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>To do list project</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
</head>
<nav id="main-nav">
    <ul>
        <li><a href="about.jsp">About App</a></li>
        <li><a href="logout.jsp">Logout</a></li>
        <li><a href="contact.jsp">Contact</a></li>
    </ul>
</nav>
<body>
<div class="container">
    <div id="aboutapp-content">
        Operation of our application:
        Using Apache Tomcat and servlets the application runs in the browser, where we can see a standard window with the option of log in or registration.
        When entering incorrect or non-existent login, displays the appropriate message.
        The same happens with registration except that in this case a message is displayed saying incorrect characters in the login or password, e.g. entering a space as a character, etc.
        If provided username is taken then we also get a message.
        After registration, we return to the main panel to be able to log in.
        Once we enter the correct data, we are transferred to our panel with lists, e.g. shopping list, homework, workout, etc. depending on which lists you had created before. Lists can be added or deleted.
        After clicking on one of the lists we are redirected to the servlet, which draws our tasks that we previously added. Tasks can be added, changed and deleted. If you do not enter the name of new list or task when creating, it will appear with the default name.
        Everything is based on saving and reading from text files. At first we thought that it will be simplier, so we decided to use this method of keeping all lists, tasks, logins and passwords.
        At this point it became quite clear that it was easier to use Hibernate or Spring. So we plan to convert it to obtain information from databases only at this moment we want to finish everything. We tried to group the project into packages and there into classes with interfaces in a logical way. File methods were tested in unit tests.
        To sum up, it is a small web application that allows you to plan some activities, e.g. training in the gym or shopping list.
        The technologies we used are: Java 8 (Intellij IDEA with the pom.xml file, files with the extension .jsp), GIT with the gitIgnore file, Front-end basics (HTML, CSS, JavaScript), Apache Tomcat, JUnit.
    </div>
</div>
<footer id="footer">
    <p>Copyright &copy; ErykAlexKubuśTEAM</p>
</footer>
</body>
</html>