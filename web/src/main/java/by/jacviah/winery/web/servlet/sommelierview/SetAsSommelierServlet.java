package by.jacviah.winery.web.servlet.sommelierview;

import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.sevice.ServiceFactory;
import by.jacviah.winery.sevice.SommService;
import by.jacviah.winery.web.WebUtils;
import by.jacviah.winery.web.servlet.LoginServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/sommelierview/setassomm")
public class SetAsSommelierServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebUtils.forward("/sommelierview/setassomm", req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        {

            ServiceFactory factory = ServiceFactory.getInstance();
            SommService service = factory.getSommService();

            String errorMessage = "";

            String name = req.getParameter("name");
            if (name == null || name.isEmpty()) {
                errorMessage = "Empty wine name, please enter name.";
            }
            if (!errorMessage.equals("")) {
                req.setAttribute("Error_Message", errorMessage);
                WebUtils.forward("/sommelierview/setassomm", req, resp);
            } else {
                if (service.setUserAsSommelier(name)) {
                    log.info("{} user role changed to sommelier", name);
                    req.setAttribute("sommelier", "Ok, " + name + " is sommelier now");
                }
                WebUtils.forward("/sommelierview/setassomm", req, resp);
            }
        }
    }
}
