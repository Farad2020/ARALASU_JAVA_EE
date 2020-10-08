package servlets;

import db.DBManager;
import modules.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "UserLoginServlet", value = "/user_login")//user_login
public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = DBManager.getUserByEmailAndPassword(email, password);
        if( user.getEmail() != null ){
            if( request.getParameter("remember_user") != null ){
                String remember_user_email = email;
                String remember_user_password = password;

                Cookie remember_user_email_cookie = new Cookie("remember_user_email", remember_user_email);
                Cookie remember_user_password_cookie = new Cookie("remember_user_password", remember_user_password);

                remember_user_email_cookie.setMaxAge(3600 * 24 * 30);
                remember_user_password_cookie.setMaxAge(3600 * 24 * 30);

                response.addCookie(remember_user_email_cookie);
                response.addCookie(remember_user_password_cookie);
            }

            request.getSession().setAttribute("current_user", user);

            //response.sendRedirect("/users?id=" + user.getId());
            response.sendRedirect("/feed");
        }else{
            response.sendRedirect("/user_login?error");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String remember_user_email = "";
        String remember_user_password = "";
        if( cookies != null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals("remember_user_email") ){
                    remember_user_email = cookie.getValue();
                }

                if(cookie.getName().equals("remember_user_password") ){
                    remember_user_password = cookie.getValue();
                }
            }
        }
        request.setAttribute("remember_user_email", remember_user_email);
        request.setAttribute("remember_user_password", remember_user_password);


        request.getRequestDispatcher("/vendor/templates/user_login.jsp").forward(request, response);
    }
}
