package org.example.servlets;

import org.example.dao.impl.UserDao;
import org.example.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/resetPwd")
public class ResetPwdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().println("<b>To reset your password fill out the form</b>");
        RequestDispatcher rd = req.getRequestDispatcher("PwdReset.html");
        rd.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        RequestDispatcher rd = req.getRequestDispatcher("PwdReset.html");
        String email = req.getParameter("email").trim();
        String pwd = req.getParameter("passwordOld");
        String pwd1 = req.getParameter("password1");
        String pwd2 = req.getParameter("password2");
        User user = new UserDao().getByEmail(email);
        if (!user.getPassword().equals(pwd)) {
            resp.getWriter().println("<b>Password wrong</b>");
            rd.include(req,resp);
            return;
        }else if (!pwd1.equals(pwd2)){
            resp.getWriter().println("<b>Passwords are not equal</b>");
            rd.include(req,resp);
            return;
        }
            user.setPassword(pwd1);
            boolean update = new UserDao().update(user);
            resp.getWriter().println(update ? "<b>Password update<br></b>" : "<b>Error:(</b>");
            rd = req.getRequestDispatcher("Login.html");
            rd.include(req, resp);



    }
}
