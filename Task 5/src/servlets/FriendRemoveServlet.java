package servlets;

import db.DBManager;
import modules.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FriendRemoveServlet", value="/remove_friend")
public class FriendRemoveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User) request.getSession().getAttribute("current_user");
        Long friend_id = Long.parseLong(request.getParameter("id"));

        if (current_user != null && friend_id != null) {
            DBManager.deleteFriend(current_user.getId(), friend_id);
            response.sendRedirect("/friends");
        }
    }
}
