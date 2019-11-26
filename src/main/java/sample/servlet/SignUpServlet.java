package sample.servlet;

import sample.list.UserService;
import sample.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter1 = req.getParameter("login");
        String parameter2 = req.getParameter("password");

        System.out.println("signupservlet: " + parameter1);
        System.out.println("signupservlet: " + parameter2);

        UserService.getInstance().createUser(parameter1, parameter2);

        resp.sendRedirect("index.jsp");
    }
}
