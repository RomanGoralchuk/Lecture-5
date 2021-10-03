package by.it.academy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Arrays;

@WebServlet(name = "ServletOne", urlPatterns = {"/one"})
public class Servlet1 extends HttpServlet {

    private static long countOfRequest = 0L;

    //////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        countOfRequest++;

        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
        }
        out.println("Client IP: " + ipAddress);
        out.println("Server IP: " + req.getLocalAddr());

        MACAddress macAddressClient = new MACAddress();
        out.println("MAC client: " + macAddressClient.getMacClient(ipAddress));

        out.println("Content-type: " + req.getContentType());
        out.println("Request URL: " + req.getRequestURL());
        out.println("Count of request: " + countOfRequest);
        out.println("Number of thread: " + Thread.currentThread().getName());
        out.println("LocalHost: " + NetworkInterface.getByInetAddress(InetAddress.getLocalHost()));
        out.println("PathInfo: " + req.getPathInfo());
        out.println("Cookies: " + Arrays.toString(req.getCookies()));


    }

}

