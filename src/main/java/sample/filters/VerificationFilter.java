package sample.filters;

import sample.list.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/HomeServlet")
public class VerificationFilter implements Filter {
    UserService userService = UserService.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (userService.verifyLoginData(login, password)){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            request.setAttribute("wrongCredentials","true");
            //HttpSession httpSession = request.getSession();
            //httpSession.setAttribute("wrongCredentials","true");
            System.out.println(request.getAttribute("wrongCredentials"));
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }

    @Override
    public void destroy() {

    }

}
