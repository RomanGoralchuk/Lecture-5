package by.itacademy.javaenterprise.goralchuk.web.servlets;

import by.itacademy.javaenterprise.goralchuk.MACAddress;
import by.itacademy.javaenterprise.goralchuk.web.filters.MACFilter;
import by.itacademy.javaenterprise.goralchuk.web.util.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

import static by.itacademy.javaenterprise.goralchuk.MACAddress.getMacClient;

@WebServlet(name = "ServletOne", urlPatterns = {"/one"})
public class Servlet1 extends HttpServlet {

    static final Logger logger = LoggerFactory.getLogger(Servlet1.class);

    AtomicLong requestCount = new AtomicLong(0);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        String ipAddress = IPUtils.getIp(req);

        requestCount.incrementAndGet();

        out.println("Client IP: " + ipAddress);
        out.println("Server IP: " + req.getLocalAddr());
        out.println("MAC client: " + getMacClient(ipAddress));
        out.println(LocalDateTime.now());

        out.println("Content-type: " + req.getContentType());
        out.println("Request URL: " + req.getRequestURL());
        out.println("Count of request: " + requestCount);
        out.println("Number of thread: " + Thread.currentThread().getName());
        out.println("LocalHost: " + NetworkInterface.getByInetAddress(InetAddress.getLocalHost()));
        out.println("PathInfo: " + req.getPathInfo());
        out.println("Cookies: " + Arrays.toString(req.getCookies()));
    }

}

