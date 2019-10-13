package by.jacviah.winery.impl;


import by.jacviah.winery.DAOProvider;
import by.jacviah.winery.SommDAO;
import by.jacviah.winery.SommService;
import by.jacviah.winery.UserDAO;
import by.jacviah.winery.exception.DaoException;

public class DefaultSommService implements SommService {

    DAOProvider provider = DAOProvider.getInstance();
    UserDAO userDAO = provider.getUserDAO();
    SommDAO sommDAO = provider.getSommDAO();

    public boolean setUserAsSommelier (String name) throws DaoException {
        return sommDAO.setUserAsSommelier(userDAO.findUser(name).getId());
    }
}
