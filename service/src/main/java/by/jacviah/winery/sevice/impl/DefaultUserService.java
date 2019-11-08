package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.DAOProvider;
import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.User;
import by.jacviah.winery.sevice.UserService;

public class DefaultUserService implements UserService {

    DAOProvider provider = DAOProvider.getInstance();
    UserDAO userDAO = provider.getUserDAO();

    @Override
    public User findUser(String name) {
        return userDAO.findUser(name);
    }

    @Override
    public User createUser(String name, String password) throws DaoException {
        User result = null;
        User user = new User(name, password);
            if (userDAO.addUser(user)) {
                result = userDAO.findUser(name);
            }
        return result;
    }
}
