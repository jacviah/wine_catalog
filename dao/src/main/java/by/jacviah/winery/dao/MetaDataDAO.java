package by.jacviah.winery.dao;

import by.jacviah.winery.dao.exception.DaoException;

import java.util.List;

public interface MetaDataDAO {
    public List<String> getCountries();
    public List<String> getCountryRegions(String countryName);
    //public int getRegionIdByName(String regionName)throws DaoException;
    //public int getGrapeIdByName(String grapeName)throws DaoException;
}
