package example_pages;

import db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddLanguageServlet", value = "/language_add")
public class AddLanguageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("languageName");
        String code = request.getParameter("languageCode");

        Language lang = new Language(name, code);
        DBManager.addLanguage(lang);
        response.sendRedirect("/admin_languages?success");
        */
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
