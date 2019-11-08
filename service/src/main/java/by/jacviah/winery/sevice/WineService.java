package by.jacviah.winery.sevice;

import by.jacviah.winery.model.Grape;
import by.jacviah.winery.model.Region;
import by.jacviah.winery.model.Wine;
import by.jacviah.winery.dao.exception.DaoException;

public interface WineService {
    public Wine findWine(String name, String winery);
    public boolean addWine(String countryName, String regionName, String grapeName, String name, String winery) throws DaoException;
}
