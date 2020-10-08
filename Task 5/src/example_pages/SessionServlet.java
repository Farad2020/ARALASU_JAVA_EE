package example_pages;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SessionServlet", value="/sessions")
public class SessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       HttpSession session = request.getSession();  // you can write request.getSession() without creating object, keep in mind
        String session_value = (String)session.getAttribute("my_session_value");

        System.out.println("Sess val == " + session_value);
        request.setAttribute("text", "Sess val == " + session_value);
        request.getRequestDispatcher("/vendor/templates/sessions.jsp").forward(request, response);
    }
}
