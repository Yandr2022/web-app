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
import java.util.Set;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<User> all = userDao.getAll();
        System.out.println("Users size: "+ all.size());
        req.setAttribute("users",all);

        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/users.jsp");
        dispatcher.forward(req,resp);
    }
}
