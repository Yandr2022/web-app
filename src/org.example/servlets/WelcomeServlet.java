package org.example.servlets;

import org.example.model.User;
import org.example.util.ServletUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        User user = ServletUtils.getSessionUser(req);
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/welcome.jsp"); ;
        dispatcher.forward(req,resp);
//        resp.getWriter().println("<b>Welcome back " + (user != null ? user.getName() + "<br><a href ='logout'>Logout</a>" : "Stranger") + "</b>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);

    }
}
