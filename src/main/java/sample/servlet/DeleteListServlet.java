package sample.servlet;

import sample.list.ListManager;
import sample.list.ListOfTasks;
import sample.list.impl.ListManagerImpl;
import sample.services.FileService;
import sample.services.impl.FileServiceImpl;
import sample.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteListServlet")
public class DeleteListServlet extends HttpServlet {
    private ListManager lm = ListManagerImpl.getInstance();
    private ListOfTasks listOfTasks;
    private FileService fileService = FileServiceImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nameOfList = request.getParameter("nameOfList");
        User user = (User) request.getSession().getAttribute("user");
        String login = user.getLogin();
        System.out.println(login);
        listOfTasks = lm.findList(nameOfList, login);
        lm.removeList(listOfTasks);
        int newListNumber = fileService.getLastNumberOfFile(request, user.getLogin());
        request.setAttribute("newListNumber", newListNumber);

        request.getRequestDispatcher("Lists.jsp").forward(request, response);
    }


}
