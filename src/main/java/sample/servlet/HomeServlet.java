package sample.servlet;


import sample.list.ListManager;
import sample.list.ListOfTasks;
import sample.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
    private ArrayList<ListOfTasks> listOfTaskLists = new ArrayList<>();
    private ListManager lm = new ListManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter1 = req.getParameter("login");
        String parameter2 = req.getParameter("password");

        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute("user");
        if (user == null || user.getLogin() == null || user.getPassword() == null) {
            user = new User();
            user.setLogin(parameter1);
            user.setPassword(parameter2);
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(3600);

        }
        //   addListsTo_ListOfTaskLists();
        try {
            for (File file : new File(req.getSession().getServletContext().getRealPath(".\\lists")).listFiles()) {
                String fileName = file.getName();
                fileName = fileName.substring(0, fileName.length() - 4);

                ListOfTasks listOfTasks = new ListOfTasks(fileName, file, new PrintWriter(new FileWriter(file, true)));
                listOfTasks.getPrintWriter().print("");
                listOfTaskLists.add(listOfTasks);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


//        PrintWriter printWriter = resp.getWriter();
//        printWriter.println("<html>");
//        printWriter.println("<body>");
//        printWriter.println("Choose your List: ");
//        printWriter.println("<br>");
//
//        for (int i = 0; i < listOfTaskLists.size(); i++) {
//            String name = listOfTaskLists.get(i).getName();
//            printWriter.println(
//                    "<form action=/LoggedInServlet>\n" +
//                            "<input type=submit name=\"nameOfList\"" + "value=" + name + ">" + "\n" +
//                            "</form>");
//        }
//
//        printWriter.println("</body>");
//        printWriter.println("</html>");
    }
}
