package by.jacviah.winery.impl;

import by.jacviah.winery.*;
import by.jacviah.winery.exception.DaoException;
import by.jacviah.winery.WineService;

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
