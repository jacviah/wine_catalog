package by.jacviah.winery.dao;

import by.jacviah.winery.dao.impl.*;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();

    //private UserDAO userDAO = DefaultUserDAO.getInstance();
    private WineDAO wineDAO = DefaultWineDAO.getInstance();
    private BottleDAO bottleDAO = DefaultBottleDAO.getInstance();
    private SommDAO sommDAO = DefaultSommDAO.getInstance();
    private RecommendDAO recDAO = DefaultRecommendDAO.getInstance();
    private MetaDataDAO metaDAO = DefaultMetaDataDAO.getInstance();


    private DAOProvider() {}

    public static DAOProvider getInstance() {

        return instance;
    }

/*    public UserDAO getUserDAO() {
        return userDAO;
    }*/

    public WineDAO getWineDAO() {
        return wineDAO;
    }

    public BottleDAO getBottleDAO() {
        return bottleDAO;
    }

    public SommDAO getSommDAO() {
        return sommDAO;
    }

    public RecommendDAO getRecommendDAO() {
        return recDAO;
    }

    public MetaDataDAO getMetaDAO() {
        return metaDAO;
    }
}
