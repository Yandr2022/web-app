package org.example.servlets.task;

import org.example.util.AppConstance;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet("/task")
public class TaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        RequestDispatcher rd = req.getRequestDispatcher("TaskLogin.html");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int min = Integer.valueOf(req.getParameter("min"));
        int max = Integer.valueOf(req.getParameter("max"));
        if (min<max) {
            RequestDispatcher rd = req.getRequestDispatcher("result");
            rd.forward(req,resp);
        } else {
            resp.setContentType("text/html");
            RequestDispatcher rd = req.getRequestDispatcher("TaskLogin.htm");
            resp.getWriter().println("Wrong minimal number");
            rd.include(req,resp);
        }

    }
}
