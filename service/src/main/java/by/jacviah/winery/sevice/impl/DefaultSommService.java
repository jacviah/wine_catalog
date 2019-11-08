package by.jacviah.winery.sevice.impl;


import by.jacviah.winery.dao.DAOProvider;
import by.jacviah.winery.dao.SommDAO;
import by.jacviah.winery.sevice.SommService;
import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.exception.DaoException;

public class DefaultSommService implements SommService {

    DAOProvider provider = DAOProvider.getInstance();
    UserDAO userDAO = provider.getUserDAO();
    SommDAO sommDAO = provider.getSommDAO();

    public boolean setUserAsSommelier (String name)  {
        return sommDAO.setUserAsSommelier(userDAO.findUser(name));
    }
}
