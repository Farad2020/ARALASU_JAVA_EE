package servlets;

import db.DBManager;
import modules.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserLoginServlet", value = "/user_login")//user_login
public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //javascript for identical passwords!!!!!!!!!!!!!!!!
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = DBManager.getUserByEmailAndPassword(email, password);
        if( user.getEmail() != null ){
            //request.getRequestDispatcher("/users?id=" + user.getId()).forward(request, response);

            request.getSession().setAttribute("current_user", user);

            response.sendRedirect("/users?id=" + user.getId());
        }else{
            response.sendRedirect("/user_login?error");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/vendor/templates/user_login.jsp").forward(request, response);
    }
}
