package org.example.servlets;

import org.example.dao.impl.OfficesDAO;
import org.example.dao.impl.UserDao;
import org.example.model.User;
import org.example.util.EncryptDecryptUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Set;

import  static org.example.util.ServletUtils.*;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private UserDao userDao = new UserDao();
    private OfficesDAO officesDAO = new OfficesDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Set<User> all = userDao.getAll();
        System.out.println("Users size: "+ all.size());
        req.setAttribute("users",all);

        String action = req.getParameter("action");
        if (action!=null)
        switch (action){
            case "C" :
                req.setAttribute("offices", officesDAO.getAll());
                forward(req,resp,"jsp/add-user.jsp");
                return;
            case "U":
                //TODO: Show user form for update
                return;
                case "D":
                //TODO: Show 'are you sure page?'
                return;
        }


        forward(req,resp,"jsp/users.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("C")){
            User user = new User();
            user.setName(req.getParameter("name").trim());
            user.setEmail(req.getParameter("email").trim().toLowerCase());
            user.setPassword(EncryptDecryptUtil.encrypt(req.getParameter("password")));

            String officeLocation = req.getParameter("office");
            user.setOffice(officesDAO.getByLocation(officeLocation));
            user.setActive(req.getParameter("isActive").equals("true"));
           boolean isAdded =  userDao.insert(user);
           req.setAttribute("msg",isAdded?"User was added":"User not added");
           forward(req,resp,"jsp/users.jsp");

        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/users.jsp");
        dispatcher.forward(req,resp);
    }
}
