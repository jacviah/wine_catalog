package by.jacviah.winery;

import by.jacviah.winery.exception.DaoException;
import by.jacviah.winery.User;

import java.util.UUID;

public interface UserService {

    public User findUser(String name) throws DaoException;
    public UUID getUserUUID(String name) throws DaoException;
    public User createUser(String name, String password) throws DaoException;
}
