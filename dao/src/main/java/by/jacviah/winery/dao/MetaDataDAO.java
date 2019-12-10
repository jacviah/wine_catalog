package by.jacviah.winery.dao;

import by.jacviah.winery.dao.entity.CountryEntity;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Country;
import by.jacviah.winery.model.Grape;
import by.jacviah.winery.model.Region;

import java.util.List;

public interface MetaDataDAO {
    public List<Country> getCountries();
    public List<Region> getCountryRegions(String countryName);
}
