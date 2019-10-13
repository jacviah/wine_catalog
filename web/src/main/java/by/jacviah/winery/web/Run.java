package by.jacviah.winery.web;

import by.jacviah.winery.sevice.ServiceFactory;
import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.model.Wine;
import by.jacviah.winery.sevice.WineService;
import by.jacviah.winery.dao.impl.DefaultUserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Run {

    private static final UserDAO userDAO = DefaultUserDAO.getInstance();
    private static final Logger log = LoggerFactory.getLogger(Run.class);


    public static void main(String args[]) {
        ServiceFactory factory = ServiceFactory.getInstance();
        WineService service = factory.getWineService();
        Wine wine;
        try {
            wine = service.findWine("wine", "chateau");
            System.out.println(wine.getRate());
        } catch (Exception e) {
            System.out.println("rrr");
        }

/*        try {
            System.out.println(service.findUser("player").toString());
        } catch (DaoException e) {
            e.printStackTrace();
        }*/
    }


}
