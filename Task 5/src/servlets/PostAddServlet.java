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

@WebServlet(name = "PostAddServlet", value = "/post_add")
public class PostAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        User current_user = (User)request.getSession().getAttribute("current_user");
        String author_id_str = request.getParameter("author_id");

        if( current_user != null && author_id_str != null){
            Long author_id = Long.parseLong(author_id_str);

            if( author_id.equals(current_user.getId()) ){
                String title = request.getParameter("post_title");
                String short_content = request.getParameter("post_short_content");
                String content = request.getParameter("post_content");
                Post post = new Post( current_user, title, short_content, content);
                DBManager.addPost(post);
                response.sendRedirect("/user_posts?success");
            }
        }else{
            response.sendRedirect("/404");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
