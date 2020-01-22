package sample.servlets;

import sample.list.ListManager;
import sample.list.ListOfTasks;
import sample.list.impl.ListManagerImpl;

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
    private String splitedTask;
    private List<String> tasks = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String nameOfList = req.getParameter("nameOfList");
        listOfTasks = lm.findList(nameOfList, login);
        List<String> listOfTasksNames = lm.getTasks(listOfTasks);

        listOfTasks = lm.findList(nameOfList, login);
        for(int i = 0 ; i < listOfTasksNames.size() ; i++){
            if(listOfTasksNames.get(i).endsWith("/") || listOfTasksNames.get(i).equals("")){            //TODO in the second occur program generates empty field when click save
                splitedTask="";
            }else{
                splitedTask = listOfTasksNames.get(i).split("/")[1];
            }
            System.out.println("splittedTask" + splitedTask);
            tasks.add(splitedTask);
        }
        List<String> characters = lm.getCharacterList(listOfTasks);
        req.setAttribute("nameOfList", nameOfList);
        req.setAttribute("characters",characters);
        req.setAttribute("listOfTasksNames", tasks);
        req.getRequestDispatcher("Tasks.jsp").forward(req, resp);
    }
}
