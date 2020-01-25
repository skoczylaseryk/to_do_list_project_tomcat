package sample.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LogOutServlet")
public class LogOutServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logout= request.getParameter("logout");

        request.setAttribute("logout",logout);

        HttpSession session = request.getSession(false);
        session.setAttribute("user",null);

        request.getRequestDispatcher("index.jsp").forward(request,response);

    }

}
