<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>To do list project</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <!--Fontawesome-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<nav id="main-nav">
    <ul>
        <li><a href="about.jsp">About App</a></li>
        <li><a href="contact.jsp">Contact</a></li>
        <%if(session!= null && session.getAttribute("user") !=null){%>
        <li id="nickLogout">

            <form action="/LogOutServlet" method="post">

                <input type="submit" id="Logout" name="Logout" value="Log out">

            </form></li>
        <%}%>
    </ul>

</nav>
<!--Contact-->
<section id="contact">
    <h2 class="section-header">Eryk <span>Skoczylas</span></h2>
    <h2 class="section-header">Alex <span>Shostak</span></h2>
    <div class="contact-data">
        <address>
            <p>ul. Aleja Zygmunta Krasińskiego 10/4 <br>
                31-061 Kraków
            </p>
            <p>tel: 000 00 00 00 <br>
                e-mail: <a href="mailto:sample@gmail.com">sample@gmail.com</a>
            </p>
            <p>https://www.linkedin.com/in/eryk-skoczylas-796533194/ <br>
                https://www.linkedin.com/in/alex-shostak-46a396115/
            </p>
        </address>
        <div class="contact-social">
            <a href=""><i class="fab fa-facebook-f"></i></a>
            <a href=""><i class="fab fa-google-plus-g"></i></a>
            <a href=""><i class="fab fa-twitter"></i></a>
            <a href=""><i class="fab fa-linkedin-in"></i></a>
        </div>
    </div>
    <form action="https://formspree.io/qbaq97@gmail.com" method="POST" class="contact-form">
        <input type="text" placeholder="Imie i nazwisko">
        <input type="email" placeholder="E-mail">
        <textarea name="" id="" placeholder="Wiadomość"></textarea>
        <input type="submit" value="Wyslij">
    </form>
</section>
<footer id="footer">
    <p>Copyright &copy; Eryk&Alex</p>
</footer>
</body>
</html>