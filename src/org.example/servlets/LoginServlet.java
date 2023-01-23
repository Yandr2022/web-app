package org.example.servlets;



import org.example.dao.impl.UserDao;
import org.example.model.User;
import org.example.util.ServletUtils;


import static org.example.util.EncryptDecryptUtil.encrypt;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


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
        final String password = encrypt(req.getParameter("pwd"));

        User user = new UserDao().getByEmail(email.trim());
        RequestDispatcher rd = req.getRequestDispatcher("login.html");
        if (user == null) {
            resp.getWriter().println("<b>User does not exist. Please <a href ='registration'>Reg</a>");
            rd.include(req, resp);
        } else if (password.equals(user.getPassword())) {
            HttpSession session = req.getSession();
            int timeout = Integer.parseInt(getServletConfig().getInitParameter("timeout"));
            session.setMaxInactiveInterval(timeout);
            //save user to the current session
            ServletUtils.saveUserSession(req,user);
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
