package by.jacviah.winery.dao;

import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.User;

public interface SommDAO {
    public boolean setUserAsSommelier(User user);
}
