package by.jacviah.winery;

import by.jacviah.winery.impl.DefaultSommDAO;
import by.jacviah.winery.impl.DefaultUserDAO;
import by.jacviah.winery.impl.DefaultWineDAO;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();

    private UserDAO userDAO = DefaultUserDAO.getInstance();
    private WineDAO wineDAO = DefaultWineDAO.getInstance();
    private SommDAO sommDAO = DefaultSommDAO.getInstance();

    private DAOProvider() {}

    public static DAOProvider getInstance() {

        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public WineDAO getWineDAO() {
        return wineDAO;
    }

    public SommDAO getSommDAO() {
        return sommDAO;
    }
}
