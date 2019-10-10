package by.jacviah.winery.service.impl;

import by.jacviah.winery.dao.DAOProvider;
import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.model.User;
import by.jacviah.winery.service.UserService;

import java.io.IOException;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    DAOProvider provider = DAOProvider.getInstance();
    UserDAO userDAO = provider.getUserDAO();
    @Override
    public User findUser(String name) throws IOException {

        return userDAO.findUser(name);
    }

    @Override
    public UUID getUserUUID(String name) throws IOException {
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();
        return userDAO.getUUID(findUser(name));
    }

    @Override
    public User createUser(String name, String password) throws IOException {

        User user = new User(name, password);
        if (findUser(user.getUsername()) == null) {
            return userDAO.addUser(user);
        } else {
            return null;
        }
    }
}
