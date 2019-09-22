package by.jacviah.winery.dao;

import by.jacviah.winery.dao.impl.MapUserDAO;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();

    private UserDAO userDAO = new MapUserDAO();

    private DAOProvider() {}

    public static DAOProvider getInstance() {

        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
