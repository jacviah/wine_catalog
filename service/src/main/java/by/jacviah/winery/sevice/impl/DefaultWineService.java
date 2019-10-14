package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.DAOProvider;
import by.jacviah.winery.dao.WineDAO;
import by.jacviah.winery.model.Wine;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.sevice.WineService;

public class DefaultWineService implements WineService {
    DAOProvider provider = DAOProvider.getInstance();
    WineDAO wineDAO = provider.getWineDAO();

    @Override
    public Wine findWine(String name, String winery) throws DaoException {
        return wineDAO.findWine(name, winery);
    }

    @Override
    public boolean addWine(String region, String grape, String name, String winery) throws DaoException {
        return wineDAO.addWine(new Wine(region, grape, name, winery));
    }
}
