package sample.filters;

import sample.services.UserService;
import sample.services.impl.UserServiceImpl;
import sample.user.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/HomeServlet")
public class VerificationFilter implements Filter {
    UserService userService = UserServiceImpl.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);

        String login;
        String password;



        if(session.getAttribute("user") != null ) {
            User user =(User)session.getAttribute("user");
             login = user.getLogin();
             password =user.getPassword();
        }else{
            login = request.getParameter("login");
            password = request.getParameter("password");
        }


        if (userService.verifyLoginData(login, password)){
            filterChain.doFilter(servletRequest,servletResponse);
        } else{
            request.setAttribute("wrongCredentials","true");
            System.out.println(request.getAttribute("wrongCredentials"));
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }

    @Override
    public void destroy() {

    }

}
