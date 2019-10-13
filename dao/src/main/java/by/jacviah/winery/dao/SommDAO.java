package by.jacviah.winery.dao;

import by.jacviah.winery.dao.exception.DaoException;

public interface SommDAO {
    public boolean setUserAsSommelier(int user_id) throws DaoException;
}
