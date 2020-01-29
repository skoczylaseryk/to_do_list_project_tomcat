package sample.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "isLogInFilter", urlPatterns = {"/Lists.jsp", "/Tasks.jsp", "/AddListServlet", "/AddTaskServlet", "/DeleteListServlet","/DeleteTaskServlet", "/ListOfTasksServlet", "/SaveTaskServlet"})
public class isLogInFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpSession session = httpRequest.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            chain.doFilter(req, resp);
        } else {
            req.setAttribute("isLogin", "noLogin");
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }
    }

    @Override
    public void destroy() {

    }
}
