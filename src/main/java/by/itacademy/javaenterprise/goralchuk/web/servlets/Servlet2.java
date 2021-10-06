package by.itacademy.javaenterprise.goralchuk.web.servlets;

import by.itacademy.javaenterprise.goralchuk.web.filters.FilterIP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Servlet2 extends HttpServlet {

    static final Logger logger = LoggerFactory.getLogger(Servlet2.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("Content-type: " + req.getContentType());
    }
}
