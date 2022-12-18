package org.example.servlets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
@WebServlet("/test")
public class TestServlet extends HttpServlet{
//    @Override
//    public void init() throws ServletException {
//        super.init();
//        System.out.println("init job");
//    }
//
//    @Override
//    public void destroy() {
//        super.destroy();
//        System.out.println("destroy job");
//    }
//
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);
//        System.out.println("service");
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<h2>Hello from servlet. Server time: "+ new Date()+"</h2>");
        printWriter.println("<a href = 'http://localhost:8080/web-app/'>home</a>");
    }
}
