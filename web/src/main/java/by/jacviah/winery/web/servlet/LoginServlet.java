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

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        {

            ServiceFactory factory = ServiceFactory.getInstance();
            UserService service = factory.getUserService();
            service.init();

            String errorMessage = "";

            String name = req.getParameter("username");
            String pass = req.getParameter("pass");

            if (name == null || name.isEmpty()) {
                errorMessage = "Empty login, please enter your login.";
            }

            if (pass.isEmpty()) {
                errorMessage = "Empty password, please enter your password.";
            }

            if (service.findUser(name) == null) {
                errorMessage = "This user does not exist, please check the login.";
            } else if (service.findUser(name).getPassword() != pass) {
                    errorMessage = "This password does not match.";
            }


            if (!errorMessage.equals("")) {
                req.setAttribute("Error_Message", errorMessage);
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            } else {
                Cookie cookieName = new Cookie("_user", service.findUser(name).getUsername());
                Cookie cookieUUID = new Cookie("_uuid", service.getUserUUID(name).toString());
                cookieName.setMaxAge(3600);
                cookieName.setPath("/winery");
                resp.addCookie(cookieName);
                cookieName.setMaxAge(3600);
                cookieName.setPath("/winery");
                resp.addCookie(cookieUUID);
                req.getRequestDispatcher("/home.jsp").forward(req, resp);
            }
        }
    }
}