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
        //TODO "Find how to add kyes and attributes inside code"
        //testing setAttribute
        //req.setAttribute("testKey", "testValue");

        //TODO "Check if user exist or not on another servlet. If not, go to SignUpServlet. If yes, go next."


        req.getRequestDispatcher("Lists.jsp").forward(req, resp);

    }
}
