package servlets;

import db.DBManager;
import modules.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "FriendSearchServlet", value = "/search_users")
public class FriendSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User) request.getSession().getAttribute("current_user");
        String full_name = request.getParameter("full_name_search");

        if (current_user != null && (full_name != null)) {
            ArrayList<User> searched_users = DBManager.getUsersByFullNamePart(full_name);

            ArrayList<User> searched_and_requested_users =  DBManager.searchRequestedUsersByUserId(current_user.getId(), full_name);

            ArrayList<User> searched_user_friends =  DBManager.searchUserFriends(current_user.getId(), full_name);

            boolean already_deleted;    //Since I have cycle in a cycle, I will use this to break from both cycles when needed
            ArrayList<User> users_to_remove = new ArrayList<>();    // An arrayList where I store users, that I need to remove from classic search
            //Very inefficient way, need to find cleaner solution

            for (User searched_user: searched_users) {    //Here I iterate through all searched users, and remove unneeded users
                already_deleted = false;

                for (User req_user: searched_and_requested_users) { //Here I add users from search, that Current user already sent his friend request
                    if(req_user.getId().equals(searched_user.getId())){
                        users_to_remove.add(searched_user);
                        already_deleted = true;
                        break;
                    }
                }

                if( !already_deleted ){
                    for (User friend_user: searched_user_friends) { //Here I add users from search, that Current user already sent his friend request
                        if(friend_user.getId().equals(searched_user.getId())){
                            users_to_remove.add(searched_user);
                            break;
                        }
                    }
                }
            }

            for (User user: users_to_remove) {
                searched_users.remove(user);
            }

            request.setAttribute("searched_users", searched_users);
            request.setAttribute("searched_and_requested_users", searched_and_requested_users);
            request.setAttribute("searched_user_friends", searched_user_friends);

            request.getRequestDispatcher("/friends").forward(request, response);
        } else {
            response.sendRedirect("/friends");
        }
    }
}
