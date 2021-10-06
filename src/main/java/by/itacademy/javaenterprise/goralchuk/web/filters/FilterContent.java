package by.itacademy.javaenterprise.goralchuk.web.filters;

import by.itacademy.javaenterprise.goralchuk.web.servlets.Servlet1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/two")
public class FilterContent extends HttpFilter {

    static final Logger logger = LoggerFactory.getLogger(FilterContent.class);

    private static final String ALLOWED_CONTENT_TYPE = "text/plain";

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String contentType = req.getHeader("content-type");
        if (ALLOWED_CONTENT_TYPE.equals(contentType)) {
            chain.doFilter(req, res);
        } else {
            res.getWriter().write("Text plain only!");
        }
    }
}
