package by.itacademy.javaenterprise.goralchuk.web.util;

import javax.servlet.http.HttpServletRequest;

public class IPUtils {

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
