package servlets;

import db.DBManager;
import modules.Chat;
import modules.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ChatSearchServlet", value="/search_chats")
public class ChatSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User) request.getSession().getAttribute("current_user");
        String chat_search = request.getParameter("chat_search");

        if (current_user != null && (chat_search != null)) {
            ArrayList<User> searched_user_friends =  DBManager.searchUserFriends(current_user.getId(), chat_search);

            ArrayList<Chat> chats = DBManager.getChats(current_user.getId());
            ArrayList<Chat> searched_chats = new ArrayList<>();

            for (Chat chat: chats ) {
                for (User friend : searched_user_friends) {
                    if(friend.getId().equals(chat.getUser_id()) || friend.getId().equals(chat.getOpponent_user_id())){
                        searched_chats.add(chat);
                    }
                }
            }

            request.setAttribute("chats", searched_chats);

            request.getRequestDispatcher("/vendor/templates/chats.jsp").forward(request, response);


        }else{
            request.getRequestDispatcher("/404").forward(request, response);
        }
    }
}
