package org.example.servlets;

import org.example.dao.impl.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activate")
public class ActivateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String email = req.getParameter("email").trim();
        boolean activate = new UserDao().activateAccount(new UserDao().getByEmail(email));
        resp.getWriter().println(activate ? "<b>" + req.getParameter("email") + " your account was activated " + "</b>" +
                "\n<a href = 'login'>Login</a>"
                : "<b>Whats wrong...</b>\n<a href ='registration'>Reg</a>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
