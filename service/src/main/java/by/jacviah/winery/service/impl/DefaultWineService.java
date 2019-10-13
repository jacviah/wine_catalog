package by.jacviah.winery.service.impl;

import by.jacviah.winery.dao.DAOProvider;
import by.jacviah.winery.dao.WineDAO;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Bottle;
import by.jacviah.winery.model.Wine;
import by.jacviah.winery.service.WineService;

import java.util.List;

public class DefaultWineService implements WineService {
    DAOProvider provider = DAOProvider.getInstance();
    WineDAO wineDAO = provider.getWineDAO();

    @Override
    public Wine findWine(String name, String winery) throws DaoException {
        return wineDAO.findWine(name, winery);
    }

    @Override
    public List<Bottle> getAllBottles(String login) throws DaoException {
        return null;
    }

    @Override
    public Wine addBottle(Bottle bottle) throws DaoException {
        return null;
    }
}
