package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.*;
import by.jacviah.winery.dao.entity.WineEntity;
import by.jacviah.winery.dao.repository.WineRepository;
import by.jacviah.winery.dao.util.mapper.WineMapper;
import by.jacviah.winery.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultWineDAO implements WineDAO {

    private static final Logger log = LoggerFactory.getLogger(DefaultWineDAO.class);

    @Autowired
    private final WineRepository repository;

    public DefaultWineDAO(WineRepository repository) {
        this.repository = repository;
    }

    @Override
    public Wine findWine(String name, String winery) {
        WineEntity entity = repository.findWineByNameAndWinery(name, winery);
        return (entity != null) ? WineMapper.toDTO(entity) : null;
    }

    @Override
    public boolean addWine(Wine wine) {
        WineEntity entity = WineMapper.toEntity(wine);
        repository.save(entity);
        return repository.existsById(entity.getId());
    }

    @Override
    public List<Wine> getWines() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "region"))
                .stream().map(entity -> WineMapper.toDTO(entity))
                .collect(Collectors.toList());
    }
}
