package by.jacviah.winery;

import by.jacviah.winery.exception.DaoException;

public interface SommService {

    public boolean setUserAsSommelier (String name) throws DaoException;
}
