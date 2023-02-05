package org.example.util;

import org.example.model.Role;
import org.example.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.example.dao.impl.UserDao.roleDao;

public class ServletUtils {
    public static final String USER = "user";
    public static void  forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request,response);

    }

    public static void saveUserSession(HttpServletRequest req, User user) {
        HttpSession session = req.getSession();
        session.setAttribute(USER, user);

//        System.out.println("LOGIN -> session ID: "+ session.getId()+" : "+new Date());
        System.out.println("User [" + user.getId() + "] in session ID: " + session.getId() + " : " + new Date());
    }

    public static User getSessionUser(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Object userObject = session.getAttribute(USER);
        if (userObject == null) {
            System.out.println("No User in session ID :" + session.getId() + " : " + new Date());
            return null;
        } else {
            return (User) userObject;
        }
    }

    public static Map<Role, List<String>> getRolesMapping() {
        Map<Role, List<String>> map = new HashMap<>();
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("C:\\Users\\Юлия\\web-app\\src\\main\\resources\\auth.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Map.Entry<Object, Object> pair : properties.entrySet()) {
            Role role = roleDao.getByName((String) pair.getKey());
            List<String> urls = Arrays.asList((((String) pair.getValue())).split(","));
            map.put(role, urls);
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(getRolesMapping().size());
    }
}
