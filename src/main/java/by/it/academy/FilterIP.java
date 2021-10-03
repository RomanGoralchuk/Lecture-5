package by.it.academy;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(urlPatterns = "/one")
public class FilterIP extends HttpFilter {

    private String foundBlackIP;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        List <String> blackListIP = new ArrayList<>();
     /*   blackListIP.add("192.168.100.28"); // real test*/
        blackListIP.add("192.168.100.30");
        blackListIP.add("192.168.100.45");
        blackListIP.add("192.168.100.7"); // real test

        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
        }

        // First check the list and find matches
        for (String s : blackListIP) {
            if (ipAddress.equals(s)) {
                foundBlackIP = s;
            }
        }

        // Block matches
        if (ipAddress.equals(foundBlackIP)) {
            res.getWriter().write("Your IP is blocked!");
        } else {
            chain.doFilter(req, res);
        }

    }
}
