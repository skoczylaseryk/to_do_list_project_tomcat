package sample.servlet;

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

@WebServlet("/ListOfTasksServlet")
public class ListOfTasksServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String nameOfList = req.getParameter("nameOfList");
        UserService userService = UserServiceImpl.getInstance();
        List<String> listOfTasksNames = new ArrayList<>();

        try {
            for (File file : new File(userService.getCONTEXTPATH() + "/lists/" + login + "/").listFiles()) {
                String fileName = file.getName();
                fileName = fileName.substring(0, fileName.length() - 4);
                if (fileName.equals(nameOfList)) {
                    ListOfTasks listOfTasks = new ListOfTasks(fileName, file, new PrintWriter(new FileWriter(file, true)));
                    String line=null;
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

        req.setAttribute("nameOfList", nameOfList);
        req.setAttribute("listOfTasksNames", listOfTasksNames);
        req.getRequestDispatcher("Tasks.jsp").forward(req, resp);
    }
}
