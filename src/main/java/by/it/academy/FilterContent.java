package by.it.academy;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/eone")
public class FilterContent extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String contentType = req.getHeader("content-type");
        if ("text/plain".equals(contentType)) {
            chain.doFilter(req, res);
        } else {
            res.getWriter().write("Text plain only!");
        }
    }
}
