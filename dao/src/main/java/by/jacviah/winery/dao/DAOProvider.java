package by.jacviah.winery.dao;

import by.jacviah.winery.dao.impl.DefaultMetaDataDAO;
import by.jacviah.winery.dao.impl.DefaultSommDAO;
import by.jacviah.winery.dao.impl.DefaultUserDAO;
import by.jacviah.winery.dao.impl.DefaultWineDAO;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();

    private UserDAO userDAO = DefaultUserDAO.getInstance();
    private WineDAO wineDAO = DefaultWineDAO.getInstance();
    private SommDAO sommDAO = DefaultSommDAO.getInstance();
    private MetaDataDAO metaDAO = DefaultMetaDataDAO.getInstance();

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

    public MetaDataDAO getMetaDAO() {
        return metaDAO;
    }
}
