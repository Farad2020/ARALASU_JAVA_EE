package servlets;

import db.DBManager;
import modules.Post;
import modules.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "RequestHandlerServlet", value = "/handle_request")
public class RequestHandlerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User) request.getSession().getAttribute("current_user");
        Long acceptor_id = Long.parseLong(request.getParameter("acceptor_id"));
        Long requester_id = Long.parseLong(request.getParameter("requester_id"));

        if (current_user != null && acceptor_id != null && requester_id != null ) {
            if (request.getParameter("confirm") != null) {

                DBManager.acceptFriendRequest(acceptor_id, requester_id);

            } else if (request.getParameter("reject") != null) {

                DBManager.rejectFriendRequest(acceptor_id, requester_id);

            }else if (request.getParameter("send") != null) {

                DBManager.addFriendRequest(acceptor_id, requester_id);

            }
            response.sendRedirect("/friends");
        } else {
            request.getRequestDispatcher("/404").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
