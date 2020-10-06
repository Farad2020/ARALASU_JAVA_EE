package example_pages;

import db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AdminLanguagesServlet", value = "/admin_languages")
public class AdminLanguagesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ArrayList<Language> languages = DBManager.getAllLanguages();

        //request.setAttribute("languages", languages);
        request.getRequestDispatcher("vendor/templates/admin_languages.jsp").forward(request, response);
    }
}
