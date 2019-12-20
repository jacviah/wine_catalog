package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.BottleDAO;
import by.jacviah.winery.model.Bottle;
import by.jacviah.winery.model.User;
import by.jacviah.winery.sevice.BottleService;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

public class DefaultBottleService implements BottleService {

    private final BottleDAO bottleDao;

    public DefaultBottleService(BottleDAO defaultBottleDao) {
        this.bottleDao = defaultBottleDao;
    }

    @Override
    @Transactional
    public boolean addBottle(Bottle bottle) {
        return bottleDao.addBottle(bottle);
    }

    @Override
    public List<Bottle> getUserBottlesByPages(User user, Pageable page) {
        return bottleDao.getUserBottles(user, page);
    }

    @Override
    public Integer countAllUserBottles(User user) {
        return bottleDao.countUserBottles(user.getId());
    }

    @Override
    @Transactional
    public boolean deleteBottle(Long id) {
        return bottleDao.removeBottle(id);
    }


}
