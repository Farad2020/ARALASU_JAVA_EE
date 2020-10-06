package servlets;

import db.DBManager;
import modules.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserSettingsServlet", value = "/user_edit")
public class UserSettingsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            User user = (User) request.getSession().getAttribute("current_user");
            Long id = Long.parseLong(request.getParameter("id"));


            System.out.println(user != null && user.getId() == id);
            if (user != null && user.getId() == id) {
                String email = request.getParameter("email");
                String full_name = request.getParameter("full_name");
                String birthdate = request.getParameter("birthdate");
                String picture_url = request.getParameter("picture_url");

                user.setEmail(email);
                user.setFull_name(full_name);
                user.setBirth_date(birthdate);
                user.setPicture_url(picture_url);
                DBManager.updateUser(user);

                request.getSession().setAttribute("current_user", user);

                response.sendRedirect("/user_edit?id="+id);//
            } else {
                request.getRequestDispatcher("/404").forward(request, response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str_id = (String) request.getParameter("id");
        User user = (User) request.getSession().getAttribute("current_user");
        if (user != null && str_id != null) {
            Long attr_id = Long.parseLong(str_id);
            if (user.getId() == attr_id) {
                request.getRequestDispatcher("/vendor/templates/user_settings.jsp").forward(request, response);//
            }
        }
        request.getRequestDispatcher("/404").forward(request, response);
    }
}
