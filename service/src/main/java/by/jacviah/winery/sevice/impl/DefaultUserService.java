package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.DAOProvider;
import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.User;
import by.jacviah.winery.sevice.UserService;

public class DefaultUserService implements UserService {

    private final UserDAO userDao;

    public DefaultUserService(UserDAO defaultUserDao) {
        this.userDao = defaultUserDao;
    }

    @Override
    public User findUser(String name) {
        return userDao.findUser(name);
    }

    @Override
    public User createUser(String name, String password) {
        User result = null;
        User user = new User(name, password);
            if (userDao.addUser(user)) {
                result = userDao.findUser(name);
            }
        return result;
    }
}
