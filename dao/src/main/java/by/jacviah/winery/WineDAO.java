package by.jacviah.winery;

import by.jacviah.winery.exception.DaoException;

public interface WineDAO {
    public Wine findWine(String name, String winery) throws DaoException;
    public <List>Bottle getAllBottles(String name) throws DaoException;
    public boolean addBottle(Bottle bottle, String login, int user_id) throws DaoException;
}
