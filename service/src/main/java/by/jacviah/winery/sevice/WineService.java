package by.jacviah.winery.sevice;

import by.jacviah.winery.model.Country;
import by.jacviah.winery.model.Grape;
import by.jacviah.winery.model.Region;
import by.jacviah.winery.model.Wine;

public interface WineService {
    public Wine findWine(String name, String winery);
    public boolean addWine(Country country, Region region, Grape grape, String name, String winery);
}
