package by.jacviah.winery.dao;

import by.jacviah.winery.model.Bottle;
import by.jacviah.winery.model.Wine;
import by.jacviah.winery.dao.exception.DaoException;

public interface WineDAO {
    public Wine findWine(String name, String winery) throws DaoException;
    public boolean addWine(Wine wine)throws DaoException;
}
