package by.jacviah.winery.impl;

import by.jacviah.winery.DAOProvider;
import by.jacviah.winery.UserDAO;
import by.jacviah.winery.exception.DaoException;
import by.jacviah.winery.User;
import by.jacviah.winery.UserService;

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
