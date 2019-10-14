package by.jacviah.winery.dao;

import by.jacviah.winery.dao.exception.DaoException;

import java.util.List;

public interface MetaDataDAO {
    public List<String> getCountryRegions(int countryId) throws DaoException;
    public int getRegionIdByName(String regionName)throws DaoException;
    public int getGrapeIdByName(String grapeName)throws DaoException;
}
