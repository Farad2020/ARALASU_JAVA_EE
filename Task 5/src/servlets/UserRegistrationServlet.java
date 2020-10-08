package servlets;

import db.DBManager;
import modules.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserRegistrationServlet", value = "/user_registration")
public class UserRegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setCharacterEncoding("UTF-8");

        //javascript for identical passwords!!!!!!!!!!!!!!!!
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String re_password = request.getParameter("re_password");
        String full_name = request.getParameter("full_name");
        String birthdate = request.getParameter("birthdate");

        if( password.equals(re_password) ){
            User user = new User( email, password, full_name, birthdate );
            DBManager.addUser(user);
            // next line is inefficient, but I used this style to create more readable code

            user = DBManager.getUserByEmail(user.getEmail());

            request.getSession().setAttribute("current_user", user);
            //request.getRequestDispatcher("/users?id=" + user.getId()).forward(request, response);

            //response.sendRedirect("/users?id=" + user.getId());
            response.sendRedirect("/feed");
        }else if( !password.equals(re_password)){
            response.sendRedirect("/user_registration?re_password_error");
        }else{      //create a check for uniqueness of an email!!!!!!!!!!!!!!!!!!!!!!!!
            response.sendRedirect("/404");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/vendor/templates/user_registration.jsp").forward(request, response);
    }
}
