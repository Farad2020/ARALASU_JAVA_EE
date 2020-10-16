package servlets;

import db.DBManager;
import modules.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SendMessageServlet", value="/send_message")
public class SendMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User) request.getSession().getAttribute("current_user");
        Long opponent_id = Long.parseLong(request.getParameter("opponent_id"));

        if (current_user != null && (opponent_id != null)) {
            String message_text = request.getParameter("message_text");
            DBManager.addOrUpdateChat(current_user.getId(), opponent_id, message_text);

            response.sendRedirect("/messages?success&id=" + opponent_id.toString() );

        }else{
            request.getRequestDispatcher("/404").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
