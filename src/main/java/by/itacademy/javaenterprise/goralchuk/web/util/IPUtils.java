package by.itacademy.javaenterprise.goralchuk.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class IPUtils {

    static final Logger logger = LoggerFactory.getLogger(IPUtils.class);

    public static String getIp(HttpServletRequest req) {
        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
        } else {
            ipAddress = ipAddress.split(";")[0];
        }
        return ipAddress;
    }
}
