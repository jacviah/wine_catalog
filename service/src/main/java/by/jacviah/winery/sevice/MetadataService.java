package by.jacviah.winery.sevice;

import by.jacviah.winery.model.Country;
import by.jacviah.winery.model.Grape;
import by.jacviah.winery.model.Region;

import java.util.List;

public interface MetadataService {
    public List<Country> getCountries();
    public List<Grape> getGrapes();
    public List<Region> getCountryRegions(Country country);
}
