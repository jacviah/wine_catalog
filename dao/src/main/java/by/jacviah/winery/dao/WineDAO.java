package by.jacviah.winery.dao;

import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Bottle;
import by.jacviah.winery.model.Wine;

import java.io.IOException;

public interface WineDAO {
    public Wine findWine(String name, String winery) throws DaoException;
    public <List>Bottle getAllBottles(String name) throws IOException;
    public boolean addBottle(Bottle bottle, String login) throws IOException;
}
