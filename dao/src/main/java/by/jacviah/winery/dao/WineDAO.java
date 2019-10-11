package by.jacviah.winery.dao;

import by.jacviah.winery.model.Bottle;
import by.jacviah.winery.model.User;
import by.jacviah.winery.model.Wine;

import java.io.IOException;

public interface WineDAO {
    public Wine findWine(String name) throws IOException;
    public <List>Bottle getAllBottles(String name) throws IOException;
    public boolean addBottle(Bottle bottle, String login) throws IOException;
}
