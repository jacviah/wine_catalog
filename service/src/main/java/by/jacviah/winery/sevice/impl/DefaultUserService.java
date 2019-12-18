package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.model.User;
import by.jacviah.winery.sevice.UserService;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public boolean createUser(String name, String password) {
        User existed = userDao.findUser(name);
        if (existed == null) {
            User user = new User(name, password);
            return  userDao.addUser(user);
        }
        return false;
    }
}
