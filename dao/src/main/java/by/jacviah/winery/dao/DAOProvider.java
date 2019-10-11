package by.jacviah.winery.dao;

import by.jacviah.winery.dao.impl.DefaultUserDAO;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();

    private UserDAO userDAO = DefaultUserDAO.getInstance();

    private DAOProvider() {}

    public static DAOProvider getInstance() {

        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
