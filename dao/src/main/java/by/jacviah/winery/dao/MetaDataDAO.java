package by.jacviah.winery.dao;

import by.jacviah.winery.dao.entity.CountryEntity;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Country;
import by.jacviah.winery.model.Grape;
import by.jacviah.winery.model.Region;

import java.util.List;

public interface MetaDataDAO {
    public List<String> getCountries();
    public List<String> getCountryRegions(String countryName);
    public Country findCountry(String name);
    public Region findRegion(String name);
    public Grape findGrape(String name);
}
