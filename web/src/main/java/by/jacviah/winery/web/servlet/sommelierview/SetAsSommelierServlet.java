package by.jacviah.winery.web.servlet.sommelierview;

import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Wine;
import by.jacviah.winery.service.ServiceFactory;
import by.jacviah.winery.service.SommService;
import by.jacviah.winery.service.WineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/sommelierview/setassomm")
public class SetAsSommelierServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/sommelierview/setassomm.jsp").forward(req, resp);
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
                req.getRequestDispatcher("/sommelierview/setassomm.jsp").forward(req, resp);
            } else {
                try {
                    if (service.setUserAsSommelier(name)) {
                        req.setAttribute("sommelier", "Ok, " + name + " is sommelier now");
                    }
                } catch (DaoException e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("/sommelierview/setassomm.jsp").forward(req, resp);
            }
        }
    }
}
