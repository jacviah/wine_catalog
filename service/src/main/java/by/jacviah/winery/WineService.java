package by.jacviah.winery;

import by.jacviah.winery.exception.DaoException;
import by.jacviah.winery.Bottle;
import by.jacviah.winery.Wine;

import java.util.List;

public interface WineService {
    public Wine findWine(String name, String winery) throws DaoException;
    public List<Bottle> getAllBottles(String login) throws DaoException;
    public Wine addBottle(Bottle bottle) throws DaoException;
}
