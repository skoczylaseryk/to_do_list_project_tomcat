package sample.servlets;

import sample.list.ListManager;
import sample.list.ListOfTasks;
import sample.list.impl.ListManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/SaveTasksServlet")
public class SaveTasksServlet extends HttpServlet {
private  ListManager lm = ListManagerImpl.getInstance();
private ListOfTasks listOfTasks;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String allTasks = request.getParameter("allTasks");
        System.out.println("allTasks" + allTasks);

        String login = request.getParameter("login");
        String nameOfList = request.getParameter("nameOfList");
        listOfTasks = lm.findList(nameOfList, login);
        List<String> characters = new ArrayList<>();
        List<String> tasks = new ArrayList<>();
        List<String> listOfAllTasks = Arrays.asList(allTasks.split(";"));

        System.out.println("listOfAllTasks" + listOfAllTasks);


        lm.writeWholeListToTxtFile(listOfAllTasks,new PrintWriter(new FileWriter("C:\\resources\\lists\\" + login + "\\" + nameOfList + ".txt")));

        System.out.println("seq characters "+ characters);

        List<String> listOfTasksNames = lm.getTasks(listOfTasks);//TODO remove this
        System.out.println(listOfTasksNames);

        request.setAttribute("listOfTasksNames", tasks);         //TODO change all listOfTasksNames to listOfAllTasks
        request.setAttribute("nameOfList", nameOfList);                     // TODO checked and unchecked
        request.setAttribute("characters", characters);
        request.getRequestDispatcher("Tasks.jsp").forward(request,response);
    }


}
