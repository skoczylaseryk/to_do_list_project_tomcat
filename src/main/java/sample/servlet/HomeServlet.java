package sample.servlet;


import sample.list.ListManager;
import sample.list.ListOfTasks;
import sample.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
<<<<<<< HEAD
=======
    private ArrayList<ListOfTasks> listOfTaskLists = new ArrayList<>();
    private ListManager lm = new ListManager();


>>>>>>> origin/Eryk(new)

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter1 = req.getParameter("login");
        String parameter2 = req.getParameter("password");

        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute("user");
        if (user == null || user.getLogin() == null || user.getPassword() == null) {
            user = new User();
            user.setLogin(parameter1);
            user.setPassword(parameter2);
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(3600);

        }
        //TODO "Find how to add keys and attributes inside code"
        //testing setAttribute
        //req.setAttribute("testKey", "testValue");

        //TODO "Check if user exist or not on another servlet. If not, go to SignUpServlet. If yes, go next."

<<<<<<< HEAD
=======
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.setProperty("webdriver.chrome.driver",".chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html>");
        printWriter.println("<body>");
        printWriter.println("Choose your List: ");
        printWriter.println("<br>");

        for (int i = 0; i < listOfTaskLists.size(); i++) {
            String name = listOfTaskLists.get(i).getName();

            if (!driver.findElements(By.id(name)).isEmpty()){

                printWriter.println(
                        "<form action=/LoggedInServlet>\n" +
                                "<input type=submit name=\"nameOfList\"" + "value=" + name + ">" + "\n" +
                                "</form>");

            }

        }
        printWriter.println("</body>");
        printWriter.println("</html>");
>>>>>>> origin/Eryk(new)

        req.getRequestDispatcher("Lists.jsp").forward(req, resp);

    }
}
