package by.jacviah.winery.web.servlet;

import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.User;
import by.jacviah.winery.service.ServiceFactory;
import by.jacviah.winery.service.UserService;
import javax.servlet.http.HttpSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegServlet", urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService service = factory.getUserService();

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

        try {
            if (service.findUser(name)!=null) {
                errorMessage = "Username already exists";
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        if (!errorMessage.equals("")) {
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
        } else {
            User user = null;
            try {
                user = service.createUser(name, pass1);
            } catch (DaoException e) {
                e.printStackTrace();
            }
            Cookie cookie = new Cookie("_user_wine_catalog", user.getUuid().toString());
            HttpSession session = req.getSession();
            session.setAttribute("name", user.getUsername());
            resp.addCookie(cookie);
            req.getRequestDispatcher("/home.jsp").forward(req, resp);
        }
    }
}