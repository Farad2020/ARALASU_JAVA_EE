package example_pages;

import db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditLanguageServlet", value = "/language_edit")
public class EditLanguageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*
        request.setCharacterEncoding("UTF-8");
        if(request.getParameter("edit") != null){
            Long id = Long.parseLong(request.getParameter("languageId"));
            String name = request.getParameter("languageName");
            String code = request.getParameter("languageCode");

            Language language = DBManager.getLanguage(id);
            if(language != null){
                language.setName(name);
                language.setCode(code);
                DBManager.updateLanguage(language);
            }
        } else if(request.getParameter("delete") != null){
            Long id = Long.parseLong(request.getParameter("languageId"));
            DBManager.deleteLanguage(id);
        }
        response.sendRedirect("/admin_languages?success");
*/
/*      Check Why We need this part
        if(request.getParameter("city") ==null)
            response.sendRedirect("/clubs?success");
        else{ //refactor this
            City city = (City)request.getAttribute("city");
            response.sendRedirect("/clubs?"+ city.getId().toString());
        }
 */
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
