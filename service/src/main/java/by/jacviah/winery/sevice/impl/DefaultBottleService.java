package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.BottleDAO;
import by.jacviah.winery.model.Bottle;
import by.jacviah.winery.model.User;
import by.jacviah.winery.sevice.BottleService;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class DefaultBottleService implements BottleService {

    private final BottleDAO bottleDao;

    public DefaultBottleService(BottleDAO defaultBottleDao) {
        this.bottleDao = defaultBottleDao;
    }

    @Override
    public boolean addBottle(Bottle bottle) {
        return bottleDao.addBottle(bottle);
    }

    @Override
    public List<Bottle> getUserBottlesByPages(User user, Pageable page) {
        return bottleDao.getUserBottles(user, page);
    }
}
