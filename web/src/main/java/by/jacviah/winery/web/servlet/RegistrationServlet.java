package by.jacviah.winery.web.servlet;

import by.jacviah.winery.service.ServiceFactory;
import by.jacviah.winery.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegServlet", urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService service = factory.getUserService();
        service.init();

        String errorMessage = "";

        String name = req.getParameter("username");
        String pass1 = req.getParameter("pass1");
        String pass2 = req.getParameter("pass2");

        if (name == null || name.isEmpty()) {
            errorMessage = "Incorrect login, please try again.";
        }

        if (pass1.isEmpty() || pass2.isEmpty() || !pass1.equals(pass2)) {
            errorMessage = "Your password and confirmation password do not match, please try again.";
        }

        if (!errorMessage.equals("")) {
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
        } else {
            req.setAttribute("user", service.createUser(name, pass1).getUsername());
            Cookie cookie = new Cookie("user", service.findUser(name).getUsername());
            req.getRequestDispatcher("/home.jsp").forward(req, resp);
        }
    }
}