package by.jacviah.winery.service.impl;

import by.jacviah.winery.dao.DAOProvider;
import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.model.User;
import by.jacviah.winery.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Override
    public User findUser(String name) throws IOException {

        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();
        List<User> all = userDAO.getAll();
        for (User record : all) {
            if (record.getUsername().equals(name)) {
                return record;
            }
        }
        return null;
    }

    @Override
    public UUID getUserUUID(String name) throws IOException {

        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();
        return userDAO.getUUID(findUser(name));
    }

    @Override
    public User createUser(String name, String password) throws IOException {
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();
        User user = new User(name, password);
        if (findUser(user.getUsername()) == null) {
            return userDAO.addUser(user);
        } else {
            return null;
        }
    }


    public void login() {
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();
        userDAO.init();
    }

    @Override
    public void init() {
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();
        userDAO.init();
    }
}
