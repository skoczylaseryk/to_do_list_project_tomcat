package sample.servlet;

import sample.list.ListManager;
import sample.list.ListOfTasks;
import sample.list.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddTaskServlet")
public class AddTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newTaskName = request.getParameter("newTaskName");
        String listName = request.getParameter("listName");
        String login = request.getParameter("login");
        UserService userService = UserService.getInstance();
        ListManager lm = new ListManager();

        try {
            for (File file : new File(userService.getCONTEXTPATH() + "/lists/" + login + "/").listFiles()) {
                String fileName = file.getName();
                fileName = fileName.substring(0, fileName.length() - 4);
                if (fileName.equals(listName)) {
                    ListOfTasks listOfTasks = new ListOfTasks(fileName, file, new PrintWriter(new FileWriter(file, true)));
                    lm.addTaskToList(listOfTasks, newTaskName);
                    break; //May creates errors
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        request.setAttribute("nameOfList", listName); //TODO when nameoflist is null after adding task
        request.getRequestDispatcher("Tasks.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
