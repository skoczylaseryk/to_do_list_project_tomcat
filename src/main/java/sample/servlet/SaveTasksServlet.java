package sample.servlet;

import sample.list.ListManager;
import sample.list.ListOfTasks;
import sample.list.impl.ListManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/SaveTasksServlet")
public class SaveTasksServlet extends HttpServlet {
    private ListManager lm = ListManagerImpl.getInstance();
    private ListOfTasks listOfTasks;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String allTasks = request.getParameter("allTasks");
        String login = request.getParameter("login");
        String nameOfList = request.getParameter("nameOfList");
        listOfTasks = lm.findList(nameOfList, login);

        List<String> listOfAllTasks = Arrays.asList(allTasks.split(";"));


        listOfAllTasks = listOfAllTasks.stream()
                .filter(e -> !Objects.equals(e, ""))
                .collect(Collectors.toList());


        lm.writeWholeListToTxtFile(listOfAllTasks, new PrintWriter(new FileWriter("C:\\resources\\lists\\" + login + "\\" + nameOfList + ".txt")));

        List<String> listOfTasksNames = lm.getTasks(listOfTasks);


        request.setAttribute("listOfTasksNames", listOfTasksNames);
        request.setAttribute("nameOfList", nameOfList);
        request.getRequestDispatcher("Tasks.jsp").forward(request, response);

    }


}
