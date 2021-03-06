package servlets;

import db.DBManager;
import modules.Chat;
import modules.Message;
import modules.Post;
import modules.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MessagesServlet", value="/messages")
public class MessagesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User)request.getSession().getAttribute("current_user");
        Long id = Long.parseLong(request.getParameter("id"));

        if( current_user != null && id!=null){
            ArrayList<Message> messages = DBManager.getChatMessages(current_user.getId(), id);
            User opponent_user = DBManager.getUser(id);

            request.setAttribute("messages", messages);
            request.setAttribute("opponent_user", opponent_user);

            request.getRequestDispatcher("/vendor/templates/messages.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/404").forward(request, response);
        }
    }
}
