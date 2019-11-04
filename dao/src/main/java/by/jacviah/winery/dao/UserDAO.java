package by.jacviah.winery.dao;

import by.jacviah.winery.model.User;
import by.jacviah.winery.dao.exception.DaoException;

import java.util.UUID;

public interface UserDAO {

    public User findUser(String name) throws DaoException;
    public boolean addUser(User user) throws DaoException;
    public boolean removeUser(String login) throws DaoException;
    public UUID getUUID(String name) throws DaoException;
}
