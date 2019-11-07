package by.jacviah.winery.dao;

import by.jacviah.winery.model.*;
import by.jacviah.winery.dao.exception.DaoException;

public interface WineDAO {
    public Wine findWine(String name, String winery);
    public boolean addWine(Wine wine) throws DaoException;
    public boolean addBottle(Bottle bottle) throws DaoException;
}
