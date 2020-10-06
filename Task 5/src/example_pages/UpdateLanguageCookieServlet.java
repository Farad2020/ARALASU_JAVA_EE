package example_pages;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateLanguageCookieServlet", value="/language_cookie")
public class UpdateLanguageCookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String style_cookie_value = request.getParameter("cookieLanguage");

        Cookie lang_cookie = new Cookie("lang_cookie", style_cookie_value);
        lang_cookie.setMaxAge(3600 * 24 * 30);
        response.addCookie(lang_cookie);

        response.sendRedirect("/home");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
