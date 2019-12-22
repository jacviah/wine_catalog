package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.MetaDataDAO;
import by.jacviah.winery.dao.WineDAO;
import by.jacviah.winery.model.Country;
import by.jacviah.winery.model.Grape;
import by.jacviah.winery.model.Region;
import by.jacviah.winery.model.Wine;
import by.jacviah.winery.sevice.WineService;

import java.util.List;

public class DefaultWineService implements WineService {

    private final WineDAO wineDao;
    private final MetaDataDAO metaDao;

    public DefaultWineService(WineDAO wineDao, MetaDataDAO metaDao) {
        this.wineDao = wineDao;
        this.metaDao = metaDao;
    }


    @Override
    public Wine findWine(String name, String winery) {
        return wineDao.findWine(name, winery);
    }

    @Override
    public Wine findWine(Long id) {
        return wineDao.findWine(id);
    }

    @Override
    public List<Wine> getWines() {
        return wineDao.getWines();
    }

    @Override
    public boolean addWine(Country country, Region region, Grape grape, String name, String winery) {

        return wineDao.addWine(Wine.WineBuilder.aWine()
                .withCountry(country)
                .withRegion(region)
                .withGrape(grape)
                .withName(name)
                .withWinery(winery)
                .build());
    }
}
