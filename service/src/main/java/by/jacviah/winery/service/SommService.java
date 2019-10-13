package by.jacviah.winery.service;

import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.User;

public interface SommService {

    public boolean setUserAsSommelier (String name) throws DaoException;
}
