package by.jacviah.winery.sevice;

import by.jacviah.winery.dao.exception.DaoException;

public interface SommService {

    public boolean setUserAsSommelier (String name) throws DaoException;
}
