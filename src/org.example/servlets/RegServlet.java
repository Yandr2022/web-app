package org.example.servlets;

import org.example.dao.impl.UserDao;
import org.example.model.User;
import org.example.util.IOUtils;
import org.example.util.Mail_Utils;
import static org.example.util.EncryptDecryptUtil.encrypt;

//import org.util.Mail_Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/registration")
public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("Reg.html");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name").trim();
        String email = req.getParameter("email").trim();

        String pwd1 = encrypt(req.getParameter("password1"));
        String pwd2 = encrypt(req.getParameter("password2"));

        RequestDispatcher dispatcher = req.getRequestDispatcher("Reg.html");
        resp.setContentType("text/html");

        if (!pwd1.equals(pwd2)){
            resp.getWriter().println("<b>Password are not equal</b>");
            dispatcher.include(req,resp);
            return;
        }

        UserDao dao = new UserDao();
        User user =dao.getByEmail(email);

        if (user != null) {
            resp.getWriter().println("<b>Email already exist! Please </b><a href = 'login'>login</a>");
            dispatcher.include(req,resp);
            return;

        }
        User user1 = new User();
        user1.setName(name);
        user1.setEmail(email);
        user1.setPassword(pwd1);
        boolean added = dao.insert(user1);
        if (added) {
//            String content = IOUtils.readFileBuff("C:\\Users\\Юлия\\web-app\\src\\main\\webapp\\templates\\activate.html");
//            content.replace("{/*}", "https://localhost:8080/web-app/activate?email=" + email);
            String content = "<a href = 'http://localhost:8080/web-app/activate?email=" + email+"'>Activate</a>";
            Mail_Utils.send(email, "user app activation", content, null);
            resp.getWriter().println("<b> Thanks for registration...Please check your mailbox</b>");
            return;
        }else {
            resp.getWriter().println("<b>Some error on server...</b>");
            dispatcher.include(req,resp);
            return;
        }
    }


}
