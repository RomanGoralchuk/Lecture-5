package by.itacademy.javaenterprise.goralchuk.web.filters;

import by.itacademy.javaenterprise.goralchuk.web.util.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(urlPatterns = "/*")
public class FilterIP extends HttpFilter {

    private static final Logger logger = LoggerFactory.getLogger(FilterIP.class);

    private final List <String> blackListIP = new ArrayList<>();

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
            logger.debug("IP address was found {}. IP address in List {}.", ipAddress, blackListIP);
        } else {
            chain.doFilter(req, res);
        }
        logger.info("IP Filter finished");
    }
}
