package by.jacviah.winery.sevice;

import by.jacviah.winery.model.User;
import by.jacviah.winery.dao.exception.DaoException;

import java.util.UUID;

public interface UserService {

    public User findUser(String name);
    public boolean createUser(String name, String password);
}
