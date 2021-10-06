package by.itacademy.javaenterprise.goralchuk.web.filters;

import by.itacademy.javaenterprise.goralchuk.MACAddress;
import by.itacademy.javaenterprise.goralchuk.web.util.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

import static javax.script.ScriptEngine.FILENAME;

@WebFilter(urlPatterns = "/one")
public class MACFilter extends HttpFilter {

    static final Logger logger = LoggerFactory.getLogger(MACFilter.class);

    final List<String> terroristListMAC = new ArrayList<>();

    {
        terroristListMAC.add("00-1a-73-03-00-00");
        terroristListMAC.add("00-1a-73-03-e5-00");
        terroristListMAC.add("00-1a-73-03-e5-7b");
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        logger.info("MAC Filter started");

        String ipAddress = IPUtils.getIp(req);

        MACAddress macAddressClient = new MACAddress();
        String clientMAC = macAddressClient.getMacClient(ipAddress);

        if (terroristListMAC.contains(clientMAC)) {
            res.getWriter().write("Sorry! Server problems. Wait for the police ");
            logger.info("Forbidden MAC address found");
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www1.nyc.gov/site/nypd/index.page"));
                } catch (URISyntaxException e) {
                    logger.error("Failed to read file {}.", FILENAME, e);
                }
            }
        } else {
            chain.doFilter(req, res);
        }
        logger.info("MAC Filter finished");
    }
}
