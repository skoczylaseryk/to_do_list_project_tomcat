package sample.servlet;

import sample.list.ListManager;
import sample.list.impl.ListManagerImpl;
import sample.list.ListOfTasks;
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

@WebServlet("/AddTaskServlet")
public class AddTaskServlet extends HttpServlet {
    private ListManager lm = ListManagerImpl.getInstance();
    private ListOfTasks listOfTasks;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newTaskName = request.getParameter("newTaskName");
        String nameOfList = request.getParameter("nameOfList");
        String login = request.getParameter("login");

         listOfTasks = lm.findList(nameOfList, login);
         lm.addTaskToList(listOfTasks,newTaskName);

        List<String> listOfTasksNames = lm.getTasks(listOfTasks);


        request.setAttribute("listOfTasksNames", listOfTasksNames);
        request.setAttribute("nameOfList", nameOfList);
        request.getRequestDispatcher("Tasks.jsp").forward(request, response);
    }
}
