package by.itacademy.javaenterprise.goralchuk;

import by.itacademy.javaenterprise.goralchuk.web.servlets.Servlet2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javax.script.ScriptEngine.FILENAME;

public class MACAddress {

    static final Logger logger = LoggerFactory.getLogger(MACAddress.class);

    public static String getMacClient(String ip) {

        String OS = System.getProperty("os.name").toLowerCase();

        String[] cmd;
        Pattern macpt = null;
        if (OS.contains("win")) {
            // Windows
            macpt = Pattern
                    .compile("[0-9a-f]+-[0-9a-f]+-[0-9a-f]+-[0-9a-f]+-[0-9a-f]+-[0-9a-f]+");
            String[] a = {"arp", "-a", ip};
            cmd = a;
        } else {
            // Mac OS X, Linux
            macpt = Pattern
                    .compile("[0-9a-f]+:[0-9a-f]+:[0-9a-f]+:[0-9a-f]+:[0-9a-f]+:[0-9a-f]+");
            String[] a = {"arp", ip};
            cmd = a;
        }

        try {
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line = reader.readLine();

            while (line != null) {
                Matcher m = macpt.matcher(line);

                if (m.find()) {
                    System.out.println("Found");
                    System.out.println("MAC: " + m.group(0));
                    return m.group(0);
                }

                line = reader.readLine();
            }

        } catch (IOException | InterruptedException e) {
            logger.error("Failed to read file {}.", FILENAME, e);
        }
        return "";
    }
}