package org.example.servlets;

import org.example.util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       if( ServletUtils.getSessionUser(req)!=null){
           System.out.println(String.format("Session Id [%s] is invalidated",req.getSession().getId()));
           req.getSession().invalidate();
           resp.getWriter().println("<b>logout is completed <a href='login'>login</a></b>");
       }else {
           System.out.println(String.format("No user found in session Id [%s] ",req.getSession().getId()));
           resp.getWriter().println("<b>No user logged found</b>");
       }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
