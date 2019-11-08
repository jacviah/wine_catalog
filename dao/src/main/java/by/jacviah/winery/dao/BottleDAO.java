package by.jacviah.winery.dao;

import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Bottle;
import by.jacviah.winery.model.User;

import java.util.List;

public interface BottleDAO {
        public boolean addBottle(Bottle bottle) throws DaoException;
        public boolean removeBottle(Bottle bottle);
        public List<Bottle> getUserBottles(User user, int page);
}
