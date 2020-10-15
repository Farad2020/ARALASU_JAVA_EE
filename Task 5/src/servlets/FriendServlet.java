package servlets;

import db.DBManager;
import modules.Post;
import modules.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "FriendServlet", value = "/friends")
public class FriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User) request.getSession().getAttribute("current_user");

        if (current_user != null) {

            if(request.getParameter("full_name_search") == null ||
                    request.getParameter("full_name_search") == ""){
                ArrayList<User> requesting_users = DBManager.getAllRequestingUsersById(current_user.getId());

                request.setAttribute("requesting_users", requesting_users);

                ArrayList<User> friends = DBManager.getAllUserFriendsById(current_user.getId());

                request.setAttribute("friends", friends);
            }

            request.getRequestDispatcher("/vendor/templates/friends.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/404").forward(request, response);
        }
    }
}
