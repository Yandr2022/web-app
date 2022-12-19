package org.example.servlets;

import org.DAO.impl.User;
import org.DAO.impl.UserDao;
import org.util.AppConstance;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        RequestDispatcher rd = req.getRequestDispatcher("Login.html");
        rd.forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        final String password = req.getParameter("pwd");
        User user = new UserDao().getByEmail(email.trim());
        RequestDispatcher rd = req.getRequestDispatcher("login.html");
        if (user == null) {
            resp.getWriter().println("<b>User does not exist. Please <a href ='registration'>Reg</a>");
            rd.include(req, resp);
        } else if (password.equals(user.getPassword())) {
            rd = req.getRequestDispatcher("welcome");
            rd.forward(req, resp);
        } else {
            resp.setContentType("text/html");
            rd = req.getRequestDispatcher("Login.html");
            resp.getWriter().println("Bad credentials");
            rd.include(req, resp);
        }
    }
}
