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

@WebServlet(name = "FeedServlet", value="/feed")
public class FeedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User)request.getSession().getAttribute("current_user");

        if( current_user != null ){
            ArrayList<Post> posts = DBManager.getAllPosts();

            request.setAttribute("posts", posts);
            request.getRequestDispatcher("/vendor/templates/feed.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/404").forward(request, response);
        }
    }
}
