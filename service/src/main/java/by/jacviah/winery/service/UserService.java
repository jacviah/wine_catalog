package by.jacviah.winery.service;

import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.User;

import java.io.IOException;
import java.util.UUID;

public interface UserService {

    public User findUser(String name) throws DaoException;
    public UUID getUserUUID(String name) throws DaoException;
    public User createUser(String name, String password) throws DaoException;
}
