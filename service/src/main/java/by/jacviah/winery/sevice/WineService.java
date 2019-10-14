package by.jacviah.winery.sevice;

import by.jacviah.winery.model.Wine;
import by.jacviah.winery.dao.exception.DaoException;

public interface WineService {
    public Wine findWine(String name, String winery) throws DaoException;
    public boolean addWine(String region, String grape, String name, String winery) throws DaoException;
}
