package sample.servlet;

import sample.list.ListManager;
import sample.list.ListOfTasks;
import sample.list.UserService;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newTaskName = request.getParameter("newTaskName");
        String nameOfList = request.getParameter("nameOfList");
        String login = request.getParameter("login");
        UserService userService = UserService.getInstance();
        List<String> listOfTasksNames = new ArrayList<>();
        ListManager lm = new ListManager();

        try {
            for (File file : new File(userService.getCONTEXTPATH() + "/lists/" + login + "/").listFiles()) {
                String fileName = file.getName();
                fileName = fileName.substring(0, fileName.length() - 4);
                if (fileName.equals(nameOfList)) {
                    ListOfTasks listOfTasks = new ListOfTasks(fileName, file, new PrintWriter(new FileWriter(file, true)));
                    lm.addTaskToList(listOfTasks, newTaskName);

                    String line = null;
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(listOfTasks.getFile()));
                    do {
                        line = bufferedReader.readLine();
                        listOfTasksNames.add(line);

                    } while (line != null);
                    bufferedReader.close();
                    listOfTasksNames.remove(listOfTasksNames.size() - 1); //because bufferedReader adds last line as null
                    break; //May creates errors
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        request.setAttribute("listOfTasksNames", listOfTasksNames);
        request.setAttribute("nameOfList", nameOfList);
        request.getRequestDispatcher("Tasks.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
