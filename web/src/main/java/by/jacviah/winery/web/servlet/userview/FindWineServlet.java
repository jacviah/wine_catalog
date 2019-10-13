package by.jacviah.winery.web.servlet.userview;

import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Wine;
import by.jacviah.winery.service.ServiceFactory;
import by.jacviah.winery.service.WineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FindWineServlet", urlPatterns = "/userview/findwine")
public class FindWineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/userview/findwine.jsp").forward(req, resp);
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

            try {
                if (service.findWine(name, winery) == null) {
                    errorMessage = "No one has ever drunk such wine yet.";
                }
            } catch (DaoException e) {
                e.printStackTrace();
            }


            if (!errorMessage.equals("")) {
                req.setAttribute("Error_Message", errorMessage);
                req.getRequestDispatcher("/userview/findwine.jsp").forward(req, resp);
            } else {
                try {
                    Wine wine = service.findWine(name, winery);
                    req.setAttribute("wine", wine);
                } catch (DaoException e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("/userview/findwine.jsp").forward(req, resp);
            }
        }
    }
}
