package by.jacviah.winery;

import by.jacviah.winery.exception.DaoException;

import java.util.UUID;

public interface UserDAO {

    public User findUser(String name) throws DaoException;
    public User addUser(User user) throws DaoException;
    public boolean removeUser(String login) throws DaoException;
    public UUID getUUID(String name) throws DaoException;
}
