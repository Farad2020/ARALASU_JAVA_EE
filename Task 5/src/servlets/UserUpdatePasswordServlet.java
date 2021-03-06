package servlets;

import db.DBManager;
import modules.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserUpdatePasswordServlet", value="/user_password_edit")
public class UserUpdatePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String password = request.getParameter("password");
        String new_password = request.getParameter("new_password");
        String re_password = request.getParameter("re_password");

        User user = (User)request.getSession().getAttribute("current_user");
        if( user != null && user.getId() == id ){
            if( !password.equals(user.getPassword()) || !new_password.equals(re_password)){
                String redirect_str = "/user_edit?&id="+id;
                if(!password.equals(user.getPassword())){
                    redirect_str += "&password_error";
                }
                if(!new_password.equals(re_password)){
                    redirect_str += "&re_password_error";
                }
                response.sendRedirect(redirect_str);
            }else{
                user.setPassword(new_password);
                DBManager.updateUser(user);

                response.sendRedirect("/user_edit?password_success&id=" + user.getId());
            }

            //request.getRequestDispatcher("/user_edit?password_success&id="+id).forward(request, response);
        }else{
            request.getRequestDispatcher("/404").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
