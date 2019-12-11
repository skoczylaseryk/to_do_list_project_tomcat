package sample.servlet;

import sample.services.FileService;
import sample.list.ListManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddListServlet")
public class AddListServlet extends HttpServlet {
    private String defaultName = "NewList";
    private int numberOfFile;
    FileService fileService = FileService.getInstance();
    ListManager lm = new ListManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newTaskListName = request.getParameter("newTaskListName");
        String login = request.getParameter("login");
        numberOfFile = fileService.getLastNumberOfFile(request,login);

        if (newTaskListName.equals("")) {
            defaultName = defaultName + numberOfFile;
            lm.createNewTaskList(login, defaultName);
            defaultName = "NewList";
        } else {
            lm.createNewTaskList(login, newTaskListName);
        }
        request.getRequestDispatcher("Lists.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
