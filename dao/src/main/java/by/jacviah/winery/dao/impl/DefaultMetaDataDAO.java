package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.MetaDataDAO;
import by.jacviah.winery.dao.entity.CountryEntity;
import by.jacviah.winery.dao.entity.GrapeEntity;
import by.jacviah.winery.dao.entity.RegionEntity;
import by.jacviah.winery.dao.repository.CountryRepository;
import by.jacviah.winery.dao.repository.GrapeRepository;
import by.jacviah.winery.dao.repository.RegionRepository;
import by.jacviah.winery.dao.util.mapper.CountryMapper;
import by.jacviah.winery.dao.util.mapper.GrapeMapper;
import by.jacviah.winery.dao.util.mapper.RegionMapper;
import by.jacviah.winery.model.Country;
import by.jacviah.winery.model.Grape;
import by.jacviah.winery.model.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DefaultMetaDataDAO implements MetaDataDAO {

    private static final Logger log = LoggerFactory.getLogger(DefaultUserDAO.class);
    private CountryRepository countryRepository;
    private RegionRepository regionRepository;
    private GrapeRepository grapeRepository;

    public DefaultMetaDataDAO(CountryRepository countryRepository,
                              RegionRepository regionRepository,
                              GrapeRepository grapeRepository) {
        this.countryRepository = countryRepository;
        this.regionRepository = regionRepository;
        this.grapeRepository = grapeRepository;
    }

    public List<Country> getCountries() {
        List<Country> countries = new ArrayList<>();
        Iterable<CountryEntity> entities = countryRepository.findAll();
        for (CountryEntity t : entities) {
            countries.add(CountryMapper.toDTO(t));
        }
        return countries;
    }

    public Country findCountry(String name) {
        CountryEntity entity = countryRepository.findByName(name);
        return (entity != null) ? CountryMapper.toDTO(entity) : null;
    }

    @Override
    public Region findRegion(String name) {
        RegionEntity entity = regionRepository.findByName(name);
        return (entity != null) ? RegionMapper.toDTO(entity) : null;
    }

    @Override
    public Grape findGrape(String name) {
        GrapeEntity entity = grapeRepository.findByName(name);
        return (entity != null) ? GrapeMapper.toDTO(entity) : null;
    }

    public List<Region> getCountryRegions(String countryName) {
        List<Region> regions = new ArrayList<>();
        List<RegionEntity> entities = regionRepository.getCountryRegions(countryName);
        if (entities.isEmpty()) {
            return regions;
        } else {
            for (RegionEntity t : entities) {
                regions.add(RegionMapper.toDTO(t));
            }
            return regions;
        }
    }

    public List<Grape> getGrapes() {
        List<Grape> countries = new ArrayList<>();
        Iterable<GrapeEntity> entities = grapeRepository.findAll();
        for (GrapeEntity t : entities) {
            countries.add(GrapeMapper.toDTO(t));
        }
        return countries;
    }
}
