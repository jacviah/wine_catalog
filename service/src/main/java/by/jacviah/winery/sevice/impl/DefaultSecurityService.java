package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.model.User;
import by.jacviah.winery.sevice.SecurityService;
import org.springframework.transaction.annotation.Transactional;

public class DefaultSecurityService implements SecurityService {

    private final UserDAO authUserDao;

    public DefaultSecurityService(UserDAO authUserDao) {
        this.authUserDao = authUserDao;
    }

    @Transactional
    public User login(String login, String password) {
        User user = authUserDao.findUser(login);
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}