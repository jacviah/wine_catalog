package by.jacviah.winery.dao;

import by.jacviah.winery.model.Country;
import by.jacviah.winery.model.Grape;
import by.jacviah.winery.model.Region;

import java.util.List;

public interface MetaDataDAO {

    public List<Country> getCountries();
    public List<Region> getCountryRegions(String countryName);
    public List<Grape> getGrapes();
    public Country findCountry(String name);
    public Region findRegion(String name);
    public Grape findGrape(String name);

}
