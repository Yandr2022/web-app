package org.example.servlets;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class TestServlet extends HttpServlet{
    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init job");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("destroy job");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        System.out.println("service");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get: ");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("Hello from servlet. Server time: "+ new Date());
    }
}
