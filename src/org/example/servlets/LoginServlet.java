package org.example.servlets;

import org.example.util.AppConstance;

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
//        pw.println("<form action='test'>");
//        pw.println("<label for='email'>Email:</label><br>");
//        pw.println("<input type='email' id='email' name='email' value='Jonh@gmail.com'><br> ");
//        pw.println("<label for='pwd'>Password:</label><br>  ");
//        pw.println("<input type='password' id='pwd' name='pwd' ><br>  ");
//        pw.println("<input type='submit' value='login'> ");
//        pw.println("</form> ");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        final String password = req.getParameter("pwd");
        if (email.trim().equalsIgnoreCase(AppConstance.DUMMY_USER_EMAIL)
                && password.equalsIgnoreCase(AppConstance.DUMMY_USER_PASSWORD)) {
            RequestDispatcher rd = req.getRequestDispatcher("welcome");
            rd.forward(req,resp);
        } else {
            resp.setContentType("text/html");
            RequestDispatcher rd = req.getRequestDispatcher("Login.html");
            resp.getWriter().println("Bad credentials");
            rd.include(req,resp);
        }
    }
}
