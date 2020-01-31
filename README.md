# to_do_project_list_tomcat

Download and run Operation of App.mp4 to see our app.

Operation of our application:
        
   Using Apache Tomcat and servlets the application runs in the browser, where we can see a standard window with the option of log in or registration.
When entering incorrect or non-existent login, displays the appropriate message.
The same happens with registration except that in this case a message is displayed saying incorrect characters in the login or password, e.g. entering a space as a character, etc.

If provided username is taken then we also get a message.
After registration, we return to the main panel to be able to log in.
Once we enter the correct data, we are transferred to our panel with lists, e.g. shopping list, homework, workout, etc. depending on which lists you had created before. Lists can be added or deleted.

After clicking on one of the lists we are redirected to the servlet, which draws our tasks that we previously added. Tasks can be added, changed and deleted. 
If you do not enter the name of new list or task when creating, it will appear with the default name.
Everything is based on saving and reading from text files. At first we thought that it will be simplier, so we decided to use this method of keeping all lists, tasks, logins and passwords. 

At this point it became quite clear that it was easier to use Hibernate or Spring. So we plan to convert it to obtain information from databases only at this moment we want to finish everything. 
We tried to group the project into packages and there into classes with interfaces in a logical way. File methods were tested in unit tests.
    
   To sum up, it is a small web application that allows you to plan some activities, e.g. training in the gym or shopping list.
The technologies we used are: Java 8 (Intellij IDEA with the pom.xml file, files with the extension .jsp), GIT with the gitIgnore file, Front-end basics (HTML, CSS, JavaScript), Apache Tomcat, JUnit.


How to run our application:
1. Clone content of repository.
2. Import project to your programming environment.
2. Download Apache Tomcat 9.0.27 or newer and run app using it.




Działanie naszej aplikacji:

   Aplikacja przy użyciu Apache Tomcat i servletów uruchamia się w przeglądarce i widzimy standardowe okno z możliwością logowania bądź rejestracji.
Przy wpisaniu nieprawidłowych bądź nieistniejących danych do logowania wyświetla odpowiedni komunikat.
Tak samo dzieje się w przypadku rejestracji z tym wyjątkiem, że w tym przypadku wyświetla się komunikat o podaniu niewłaściwych znaków w loginie lub haśle np. podanie spacji jako znaku itp.

Jeśli nazwa użytkownika jest zajęta wtedy również dostajemy stosowny komunikat.
Po rejestracji wracamy spowrotem do głównego panelu aby móc się zalogować. 
Gdy już wprowadzimy prawidłowe dane zostajemy przeniesieni do naszego panelu z listami np. shopping list, homework, workout itp.
w zależności od tego jaką listę utworzymy. Listy można dodawac i usuwać.

Po kliknięciu w którąś z list zostajemy przekierowani do servletu, który zaciąga nasze taski. Taski można dodawać, usuwać i zmieniać ich nazwę.
Jeśli nie podamy nazwy listy lub taska przy tworzeniu pojawi się on z nazwą domyślną.
Wszystko opiera się na zapisywaniu i zczytywaniu z plików tekstowych, początkowo myśleliśmy,
że będzie nieco prościej a i tak nas nieco przerastał ten projekt więc na taką metodę trzymania wszystkich list, tasków, loginów, haseł się zdecydowaliśmy.

W tym momencie stało się dość jasne, że łatwiej było zastosować Hibernate'a lub Springa ale planujemy to przerobić tak żeby czerpać informacje z baz danych
tylko na ten moment chcemy dopiąć wszystko na ostatni guzik. Projekt staraliśmy się rozbić na paczki a w nich na klasy wraz z interfejsami w miare logiczny sposób

   Reasumując, jest to mała aplikacja webowa pozwalająca rozplanować nam jakieś czynności np. trening na siłowni czy listę zakupów.
Technologie które użyliśmy to przedewszystkim: Java 8 (Intellij IDEA wraz z plikiem pom.xml, plikami z rozszerzeniem .jsp), GIT wraz z plikiem gitIgnore, podstawy Front-endu(HTML, CSS, JavaScript), Apache Tomcat. 


Jak uruchomić naszą aplikację:
1. Sklonuj zawartość repozytorium na nasz komputer.
2. Zaimportuj projekt do swojego środowiska programistycznego.
3. Pobierz Apache Tomcat 9.0.27  lub nowszy i uruchom aplikację używając go.

https://www.linkedin.com/in/eryk-skoczylas-796533194/
https://www.linkedin.com/in/alex-shostak-46a396115/


PS: We at the start of Java courses :)
<img src="https://i.ibb.co/jWbs9bp/60874300-2040272576266467-1734915510522347520-n.jpg" alt="60874300-2040272576266467-1734915510522347520-n" border="0">
