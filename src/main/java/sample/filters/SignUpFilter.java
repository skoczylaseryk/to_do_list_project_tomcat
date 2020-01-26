package sample.filters;

import sample.services.UserService;
import sample.services.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "SignUpServlet", urlPatterns = "/SignUpServlet")
public class SignUpFilter implements Filter {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String login = request.getParameter("login");
        int verifyResult = userService.verifySignUpData(login);
        if (verifyResult == 0) {
            request.setAttribute("verifyResult", "0");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        } else if (verifyResult == 1) {
            request.setAttribute("verifyResult", "1");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        } else if (verifyResult == 2) {
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
