package by.jacviah.winery.web.servlet.userview;

import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Wine;
import by.jacviah.winery.sevice.ServiceFactory;
import by.jacviah.winery.sevice.WineService;
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

@WebServlet(urlPatterns = "/userview/addwine")
public class AddWineServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebUtils.forward("/userview/addwine", req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        {

            ServiceFactory factory = ServiceFactory.getInstance();
            WineService service = factory.getWineService();

            String errorMessage = "";

            String name = req.getParameter("wine");
            String winery = req.getParameter("winery");
            String region = req.getParameter("region");
            String grape = req.getParameter("grape");

            if (name == null || name.isEmpty()) {
                errorMessage = "Empty wine name, please enter name.";
            }

            if (winery.isEmpty()) {
                errorMessage = "Empty winery name, please enter winery.";
            }

            if (region.isEmpty()) {
                errorMessage = "Empty region, please enter.";
            }

            if (grape.isEmpty()) {
                errorMessage = "Empty grape, please enter.";
            }

            try {
                if (service.findWine(name, winery) != null) {
                    errorMessage = "Wine is exist";
                }
            } catch (DaoException e) {
                log.error("error - method  service.addWine() call");
                resp.setStatus(503);
            }

            if (!errorMessage.equals("")) {
                req.setAttribute("Error_Message", errorMessage);
            } else {
                try {
                    boolean wine = service.addWine(region, grape, name, winery);
                    if (wine) {
                        Wine created = service.findWine(name, winery);
                        req.setAttribute("wine", created);
                    }
                } catch (DaoException e) {
                    log.error("error - method  service.addWine() call");
                    resp.setStatus(503);
                }
            }
            WebUtils.forward("/userview/addwine", req, resp);
        }
    }
}

