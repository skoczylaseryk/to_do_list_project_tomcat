package sample.servlet;

import sample.list.ListManager;
import sample.list.ListOfTasks;
import sample.list.impl.ListManagerImpl;
import sample.services.UserService;
import sample.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ListOfTasksServlet")
public class ListOfTasksServlet extends HttpServlet {
    private ListManager lm = ListManagerImpl.getInstance();
    private ListOfTasks listOfTasks;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String nameOfList = req.getParameter("nameOfList");

        listOfTasks = lm.findList(nameOfList, login);

        List<String> listOfTasksNames = lm.getTasks(listOfTasks);

        req.setAttribute("nameOfList", nameOfList);
        req.setAttribute("listOfTasksNames", listOfTasksNames);
        req.getRequestDispatcher("Tasks.jsp").forward(req, resp);
    }
}
