package by.jacviah.winery.dao;

import by.jacviah.winery.model.*;
import by.jacviah.winery.dao.exception.DaoException;

import java.util.List;

public interface WineDAO {
    public Wine findWine(String name, String winery);
    public Wine findWine(Long id);
    public boolean addWine(Wine wine);
    public List<Wine> getWines();
}
