package by.itacademy.javaenterprise.goralchuk;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
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

@WebFilter(urlPatterns = "/one")
public class MACFilter extends HttpFilter {

    private String foundTerroristMAC;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        List<String> terroristListMAC = new ArrayList<>();
        terroristListMAC.add("00-1a-73-03-00-00");
        terroristListMAC.add("00-1a-73-03-e5-00");
        terroristListMAC.add("00-1a-73-03-e5-7b");

        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
        }
        MACAddress macAddressClient = new MACAddress();
        String clientMAC =  macAddressClient.getMacClient(ipAddress);

        for (String s : terroristListMAC) {
            if (clientMAC.equals(s)) {
                foundTerroristMAC = s;

                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://www1.nyc.gov/site/nypd/index.page"));
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if (clientMAC.equals(foundTerroristMAC)) {
            res.getWriter().write("Sorry! Server problems. Wait for the police ");
        } else {
            chain.doFilter(req, res);
        }
    }
}
