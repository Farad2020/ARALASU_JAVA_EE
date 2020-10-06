package servlets;

import db.DBManager;
import example_pages.Language;
import modules.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "HomeServlet", value = "/home")//
public class HomeServlet extends HttpServlet {
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

        //Language cookie
        String lang_cookie_value = "ENG";
        if( cookies != null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals("lang_cookie") ){
                    lang_cookie_value = cookie.getValue();
                    break;
                }
            }
        }
        request.setAttribute("lang_cookie_value", lang_cookie_value);
        */

        //Basic Info
        ArrayList<User> users = DBManager.getAllUsers();

        request.setAttribute("users", users);


        request.getRequestDispatcher("/vendor/templates/home.jsp").forward(request, response);
    }
}
