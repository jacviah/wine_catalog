package by.jacviah.winery.service;

import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.impl.DefaultUserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Run {

    private static final UserDAO userDAO = DefaultUserDAO.getInstance();
    private static final Logger log = LoggerFactory.getLogger(Run.class);


    public static void main(String args[]) {
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService service = factory.getUserService();
        try {
            service.createUser("rrr", "try");
        } catch (Exception e) {
            System.out.println("rrr");
        }
    }


}
