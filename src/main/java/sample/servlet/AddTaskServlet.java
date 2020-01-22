package sample.servlets;

import sample.list.ListManager;
import sample.list.impl.ListManagerImpl;
import sample.list.ListOfTasks;

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
    private String splitedTask;
    private List<String> tasks = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newTaskName = request.getParameter("newTaskName");
        String nameOfList = request.getParameter("nameOfList");
        String login = request.getParameter("login");

         listOfTasks = lm.findList(nameOfList, login);
         lm.addTaskToList(listOfTasks,newTaskName);

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

        request.setAttribute("listOfTasksNames", tasks);
        request.setAttribute("characters",characters);
        request.setAttribute("nameOfList", nameOfList); //TODO when nameOfList is null after adding task
        request.getRequestDispatcher("Tasks.jsp").forward(request, response);
    }
}
