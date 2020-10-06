package example_pages;

import db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SingularNewsPageServlet",value = "/news")
public class SingularNewsPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        Cookie[] cookies = request.getCookies();

        //Stylesheet Cookie
        String style_cookie_value = "default_body";
        String style_cookie_name = "Default";
        ArrayList<StyleType> styleTypes = DBManager.getAllStyleTypes();

        if( cookies != null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals("style_cookie") ){
                    style_cookie_value = cookie.getValue();
                    style_cookie_name = DBManager.getStyleByCss(style_cookie_value).getName();
                    break;
                }
            }
        }
        request.setAttribute("style_cookie_value", style_cookie_value);
        request.setAttribute("style_cookie_name", style_cookie_name);
        request.setAttribute("styleTypes", styleTypes);

        Long id = Long.parseLong(request.getParameter("id"));
        News news = DBManager.getNews(id);
        ArrayList<Language> languages = DBManager.getAllLanguages();
        ArrayList<Publication> publications = DBManager.getAllPublications();

        request.setAttribute("news", news);
        request.setAttribute("languages", languages);
        request.setAttribute("publications", publications);
        request.getRequestDispatcher("vendor/templates/singular_news_page.jsp").forward(request, response);

         */
    }
}
