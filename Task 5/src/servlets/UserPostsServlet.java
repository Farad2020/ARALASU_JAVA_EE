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

@WebServlet(name = "UserPostsServlet", value="/user_posts")
public class UserPostsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User)request.getSession().getAttribute("current_user");

        if( current_user != null ){

            ArrayList<Post> posts = DBManager.getPostsByAuthorId(current_user.getId());

            request.setAttribute("posts", posts);

            request.getRequestDispatcher("/vendor/templates/user_posts.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/404").forward(request, response);
        }
    }
}
