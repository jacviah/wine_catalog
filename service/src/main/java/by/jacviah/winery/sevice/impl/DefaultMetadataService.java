package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.MetaDataDAO;
import by.jacviah.winery.model.Country;
import by.jacviah.winery.model.Grape;
import by.jacviah.winery.model.Region;
import by.jacviah.winery.sevice.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultMetadataService implements MetadataService {

    MetaDataDAO dao;

    public DefaultMetadataService(MetaDataDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Country> getCountries() {
        return dao.getCountries();
    }

    @Override
    public List<Grape> getGrapes() {
        return dao.getGrapes();
    }

    @Override
    public List<Region> getCountryRegions(Country country) {
        return dao.getCountryRegions(country.getName());
    }
}
