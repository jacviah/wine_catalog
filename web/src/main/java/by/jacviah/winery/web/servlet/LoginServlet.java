package by.jacviah.winery.web.servlet;

import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Role;
import by.jacviah.winery.model.User;
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

            String errorMessage = "";

            String name = req.getParameter("username");
            String pass = req.getParameter("pass");

            if (name == null || name.isEmpty()) {
                errorMessage = "Empty login, please enter your login.";
            }

            if (pass.isEmpty()) {
                errorMessage = "Empty password, please enter your password.";
            }

            try {
                if (service.findUser(name) == null) {
                    errorMessage = "This user does not exist, please check the login.";
                } else if (!service.findUser(name).getPassword().equals(pass)) {
                        errorMessage = "This password does not match. - " + service.findUser(name).getPassword();
                }
            } catch (DaoException e) {
                e.printStackTrace();
            }


            if (!errorMessage.equals("")) {
                req.setAttribute("Error_Message", errorMessage);
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            } else {
                try {
                    User user =  service.findUser(name);
                    Cookie cookieName = new Cookie("_name",user.getUsername());
                    Cookie cookieUUID = new Cookie("_uuid", user.getUuid().toString());
                    resp.addCookie(cookieName);
                    resp.addCookie(cookieUUID);
                    if (user.getRole()== Role.USER) {
                        req.getRequestDispatcher("/userview/findwine.jsp").forward(req, resp);
                    }
                } catch (DaoException e) {
                    e.printStackTrace();
                }
                req.getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
            }
        }
    }
}