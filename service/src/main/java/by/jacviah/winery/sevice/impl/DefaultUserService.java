package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.DAOProvider;
import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.User;
import by.jacviah.winery.sevice.UserService;

import java.util.UUID;

public class DefaultUserService implements UserService {
    DAOProvider provider = DAOProvider.getInstance();
    UserDAO userDAO = provider.getUserDAO();
    @Override
    public User findUser(String name) throws DaoException {

        return userDAO.findUser(name);
    }

    @Override
    public UUID getUserUUID(String name) throws DaoException {
        DAOProvider provider = DAOProvider.getInstance();
        return userDAO.getUUID(name);
    }

    @Override
    public User createUser(String name, String password) throws DaoException {

        User user = new User(name, password);
        if (findUser(name) == null) {
            return userDAO.addUser(user);
        } else {
            return null;
        }
    }
}
