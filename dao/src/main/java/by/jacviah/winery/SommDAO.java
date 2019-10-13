package by.jacviah.winery;

import by.jacviah.winery.exception.DaoException;

public interface SommDAO {
    public boolean setUserAsSommelier(int user_id) throws DaoException;
}
