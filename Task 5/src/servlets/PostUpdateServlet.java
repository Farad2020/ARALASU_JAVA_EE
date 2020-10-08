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

@WebServlet(name = "PostUpdateServlet", value="/post_update")
public class PostUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        User current_user = (User)request.getSession().getAttribute("current_user");
        try{
            Long post_id = Long.parseLong(request.getParameter("post_id"));
            Long author_id = Long.parseLong(request.getParameter("post_author_id"));

            if( current_user != null && author_id == current_user.getId()) {

                if(request.getParameter("update") != null){
                    String title = request.getParameter("post_title");
                    String short_content = request.getParameter("post_short_content");
                    String content = request.getParameter("post_content");

                    Post post = DBManager.getPostById(post_id);
                    if(post != null){
                        post.setTitle(title);
                        post.setShort_content(short_content);
                        post.setContent(content);

                        DBManager.updatePost(post);
                    }
                } else if(request.getParameter("delete") != null){
                    DBManager.deletePost(post_id);
                }
            }else{

            }
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/404");
        }



    }
/*
* if(request.getParameter("update") != null){
            Long id = Long.parseLong(request.getParameter("newsId"));
            String title = request.getParameter("newsTitle");
            String short_content = request.getParameter("newsShortContent");
            String content = request.getParameter("newsContent");
            Long lang_id = Long.parseLong(request.getParameter("newsLanguageId"));
            Long pub_id = Long.parseLong(request.getParameter("newsPublicationId"));
            String pic_url = request.getParameter("newsPictureUrl");

            Language language = DBManager.getLanguage(lang_id);
            Publication publication = DBManager.getPublication(pub_id);

            News news = DBManager.getNews(id);
            if(news != null){
                news.setTitle(title);
                news.setShort_content(short_content);
                news.setContent(content);
                news.setShort_content(short_content);
                news.setLanguage(language);
                news.setPublication(publication);

                news.setPicture_url(pic_url);
                DBManager.updateNews(news);
            }
        } else if(request.getParameter("delete") != null){
            Long id = Long.parseLong(request.getParameter("newsId"));
            DBManager.deleteNews(id);
        }
* */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
