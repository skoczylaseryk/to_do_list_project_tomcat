package sample.servlet;

import sample.list.ListManager;
import sample.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AddListServlet")
public class AddListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newTaskListName = request.getParameter("newTaskListName");
        String login = request.getParameter("login");
        ListManager lm = new ListManager();

        lm.createNewTaskList(login, newTaskListName);
        request.getRequestDispatcher("Lists.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
