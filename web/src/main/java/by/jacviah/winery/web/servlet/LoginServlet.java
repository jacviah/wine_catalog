package by.jacviah.winery.web.servlet;

import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Role;
import by.jacviah.winery.model.User;
import by.jacviah.winery.sevice.ServiceFactory;
import by.jacviah.winery.sevice.UserService;
import by.jacviah.winery.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebUtils.forward("login", req, resp);
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
                log.error("error - method  service.findUser() call");
                resp.setStatus(503);
            }


            if (!errorMessage.equals("")) {
                req.setAttribute("Error_Message", errorMessage);
                WebUtils.forward("login", req, resp);
            } else {
                try {
                    User user =  service.findUser(name);
                    Cookie cookieName = new Cookie("name", URLEncoder.encode(user.getUsername(), "UTF-8"));
                    Cookie cookieUUID = new Cookie("uuid", URLEncoder.encode(user.getUuid().toString(), "UTF-8"));
                    resp.addCookie(cookieName);
                    resp.addCookie(cookieUUID);
                    if (user.getRole()== Role.USER) {
                        log.info("user {} logged as {}", user.getUsername(), user.getRole().toString());
                        WebUtils.redirect("/userview/findwine", req, resp);
                    }
                    if (user.getRole()== Role.SOMMELIER) {
                        log.info("user {} logged as {}", user.getUsername(), user.getRole().toString());
                        WebUtils.redirect("/sommelierview/setassomm", req, resp);
                    }
                } catch (DaoException e) {
                    log.error("error - method  service.findUser() call");
                    resp.setStatus(503);
                }
            }
        }
    }
}