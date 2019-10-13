package by.jacviah.winery.dao;

import by.jacviah.winery.dao.impl.DefaultUserDAO;
import by.jacviah.winery.dao.impl.DefaultWineDAO;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();

    private UserDAO userDAO = DefaultUserDAO.getInstance();
    private WineDAO wineDAO = DefaultWineDAO.getInstance();

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
}
