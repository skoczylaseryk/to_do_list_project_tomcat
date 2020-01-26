package sample.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "isLogInFilter", urlPatterns = {"/Lists.jsp", "/Tasks.jsp", "/AddListServlet", "/AddTaskServlet", "/DeleteTaskServlet", "/ListOfTasksServlet", "/SaveTaskServlet"})
public class isLogInFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpSession session = httpRequest.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            chain.doFilter(req, resp);
        } else {
            req.setAttribute("isLogin", "noLogin");
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
