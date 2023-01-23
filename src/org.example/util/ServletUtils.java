package org.example.util;

import org.example.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class ServletUtils {
    public static final String USER = "user";

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
}
