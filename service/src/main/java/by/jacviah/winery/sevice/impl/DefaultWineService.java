package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.DAOProvider;
import by.jacviah.winery.dao.MetaDataDAO;
import by.jacviah.winery.dao.WineDAO;
import by.jacviah.winery.model.Country;
import by.jacviah.winery.model.Grape;
import by.jacviah.winery.model.Region;
import by.jacviah.winery.model.Wine;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.sevice.WineService;

public class DefaultWineService implements WineService {
    DAOProvider provider = DAOProvider.getInstance();
    WineDAO wineDAO = provider.getWineDAO();
    MetaDataDAO metaDAO = provider.getMetaDAO();

    @Override
    public Wine findWine(String name, String winery) {
        return wineDAO.findWine(name, winery);
    }

    @Override
    public boolean addWine(String countryName, String regionName, String grapeName, String name, String winery) throws DaoException {
        Grape grape = metaDAO.findGrape(grapeName);
        Region region = metaDAO.findRegion(regionName);
        Country country = metaDAO.findCountry(countryName);

        return wineDAO.addWine(Wine.WineBuilder.aWine()
                .withCountry(country)
                .withRegion(region)
                .withGrape(grape)
                .withName(name)
                .withWinery(winery)
                .build());
    }
}
