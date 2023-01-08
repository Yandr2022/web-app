package org.example.servlets.task;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/task")
public class TaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        RequestDispatcher rd = req.getRequestDispatcher("TaskLogin.html");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int min = Integer.parseInt(req.getParameter("min"));
        int max = Integer.parseInt(req.getParameter("max"));
        if (min<max) {
            RequestDispatcher rd = req.getRequestDispatcher("result");
            rd.forward(req,resp);
        } else {
            resp.setContentType("text/html");
            RequestDispatcher rd = req.getRequestDispatcher("TaskLogin.html");
            resp.getWriter().println("Wrong minimal number");
            rd.include(req,resp);
        }

    }
}
