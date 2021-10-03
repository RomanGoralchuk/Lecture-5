package by.it.academy;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "ServletOne", urlPatterns = {"/two"})
public class Servlet2 extends HttpServlet {
}
