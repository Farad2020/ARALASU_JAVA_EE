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

@WebServlet(name = "UserPageServlet", value = "/users")
public class UserPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        User user = DBManager.getUser(id);
        User current_user = (User)request.getSession().getAttribute("current_user");
        //Create age calculating method for user

        // Later maybe other user can see other user, so keep in mind new interactions it may cause
        if( user.getId() != null && current_user != null && current_user.getId() != user.getId()){
            request.setAttribute("other_user", user);

            request.setAttribute("posts", DBManager.getPostsByAuthorId(user.getId()));

            request.getRequestDispatcher("/vendor/templates/user_page.jsp").forward(request, response);
        }else if(user.getId() != null && current_user != null && current_user.getId() == user.getId()){
            request.getRequestDispatcher("/user_posts");
        }else{
            request.getRequestDispatcher("/404").forward(request, response);
        }
    }
}
// We have a problem where I use if instead of try catch. Later should be refactored, cause in general use can cause problems
