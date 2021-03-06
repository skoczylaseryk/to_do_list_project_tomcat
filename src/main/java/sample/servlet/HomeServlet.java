package sample.servlet;


import sample.FileComparator;
import sample.services.FileService;
import sample.services.UserService;
import sample.services.impl.FileServiceImpl;
import sample.services.impl.UserServiceImpl;
import sample.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
    FileService fileService = FileServiceImpl.getInstance();
    UserService userServiceImpl = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute("user");
        if (user == null || user.getLogin() == null || user.getPassword() == null) {
            user = new User();
            user.setLogin(login);
            user.setPassword(password);
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(3600);

        }
        int newListNumber = fileService.getLastNumberOfFile(req, user.getLogin());
        req.setAttribute("newListNumber", newListNumber);


        req.getRequestDispatcher("Lists.jsp").forward(req, resp);


    }
}