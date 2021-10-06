package by.itacademy.javaenterprise.goralchuk.web.filters;

import by.itacademy.javaenterprise.goralchuk.web.util.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static javax.script.ScriptEngine.FILENAME;

@WebFilter(urlPatterns = "/one")
public class FilterIP extends HttpFilter {

    static final Logger logger = LoggerFactory.getLogger(FilterIP.class);

    final List <String> blackListIP = new ArrayList<>();

    {
        blackListIP.add("192.168.100.30");
        blackListIP.add("192.168.100.45");
        blackListIP.add("192.168.100.7");
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        logger.info("IP Filter started");

        String ipAddress = IPUtils.getIp(req);

        if (blackListIP.contains(ipAddress)) {
            res.getWriter().write("Your IP is blocked!");
            logger.info("Forbidden IP address found");
        } else {
            chain.doFilter(req, res);
        }
        logger.info("MAC Filter finished");
    }
}
