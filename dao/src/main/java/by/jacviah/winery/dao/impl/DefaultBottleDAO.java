package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.*;
import by.jacviah.winery.dao.entity.BottleEntity;
import by.jacviah.winery.dao.repository.BottleRepository;
import by.jacviah.winery.dao.util.EMUtil;
import by.jacviah.winery.dao.util.mapper.BottleMapper;
import by.jacviah.winery.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class DefaultBottleDAO implements BottleDAO {

    private static final Logger log = LoggerFactory.getLogger(DefaultBottleDAO.class);

    BottleRepository repository;

    public DefaultBottleDAO(BottleRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean addBottle(Bottle bottle) {
        BottleEntity entity = BottleMapper.toEntity(bottle);
        repository.save(entity);
        return repository.existsById(entity.getId());
    }

    @Override
    public List<Bottle> getUserBottles(User user, Pageable pageable) {
        List<Bottle> bottles = new ArrayList<>();
        List<BottleEntity> entities = repository.getUserBottles(user.getId(), pageable);
        if (entities.isEmpty()) {
            return bottles;
        } else {
            for (BottleEntity t : entities) {
                bottles.add(BottleMapper.toDTO(t));
            }
            return bottles;
        }
    }

    @Override
    public boolean removeBottle(Long id) {
        repository.deleteById(id);
        return (repository.findById(id) == null);
    }
}