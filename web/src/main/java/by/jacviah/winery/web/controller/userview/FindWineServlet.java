package by.jacviah.winery.web.controller.userview;

import by.jacviah.winery.model.Wine;
import by.jacviah.winery.sevice.WineService;
import by.jacviah.winery.web.WebUtils;
import by.jacviah.winery.web.controller.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/userview/findwine")
public class FindWineServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebUtils.forward("/userview/findwine", req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        {

            ServiceFactory factory = ServiceFactory.getInstance();
            WineService service = factory.getWineService();

            String errorMessage = "";

            String name = req.getParameter("wine");
            String winery = req.getParameter("winery");

            if (name == null || name.isEmpty()) {
                errorMessage = "Empty wine name, please enter name.";
            }

            if (winery.isEmpty()) {
                errorMessage = "Empty winery name, please enter winery.";
            }

            if (service.findWine(name, winery) == null) {
                errorMessage = "No one has ever drunk such wine yet.";
            }

            if (!errorMessage.equals("")) {
                req.setAttribute("Error_Message", errorMessage);
            } else {
                Wine wine = service.findWine(name, winery);
                req.setAttribute("wine", wine);
            }
            WebUtils.forward("/userview/findwine", req, resp);
        }
    }
}
