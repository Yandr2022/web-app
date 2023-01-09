package org.example.servlets;

import org.example.dao.impl.UserDao;
import org.example.model.User;
import org.example.util.Mail_Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.example.util.PswGenerator.getPsw;


@WebServlet("/forgotPwd")
public class PwdGenerateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().println("<b>Enter the password that you specified during registration</b>");
        RequestDispatcher rd = req.getRequestDispatcher("PwdGener.html");
        rd.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email").trim();
        resp.setContentType("text/html");
        User user = new UserDao().getByEmail(email);
        if (user == null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("PwdGener.html");
            resp.getWriter().println("<b>Email user: " + email + " not found, please try again, " +
                    "or <a href='http://localhost:8080/web-app/registration'>register</a></b>");
            dispatcher.include(req, resp);

        } else {
            String psw = getPsw(email);
            String content = "<b>New password :" + psw + "<br><a href='http://localhost:8080/web-app/resetPwd?email="
                    + email + "'>Reset</a><br><a href='http://localhost:8080/web-app/login'>Login</a></b>";
            boolean send = Mail_Utils.send(email, "New password", content, null);
            user.setPassword(psw);
            boolean update = new UserDao().update(user);
            resp.getWriter().println(send && update ? "<b>New password sending...Please check your mailbox</b>"
                    : "<b>Whats wrong....</b>");

        }
    }
}

