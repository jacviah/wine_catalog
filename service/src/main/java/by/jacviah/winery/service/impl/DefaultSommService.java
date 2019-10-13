package by.jacviah.winery.service.impl;


import by.jacviah.winery.dao.DAOProvider;
import by.jacviah.winery.dao.SommDAO;
import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.WineDAO;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.service.SommService;

public class DefaultSommService implements SommService{

    DAOProvider provider = DAOProvider.getInstance();
    UserDAO userDAO = provider.getUserDAO();
    SommDAO sommDAO = provider.getSommDAO();

    public boolean setUserAsSommelier (String name) throws DaoException {
        return sommDAO.setUserAsSommelier(userDAO.findUser(name).getId());
    }
}
