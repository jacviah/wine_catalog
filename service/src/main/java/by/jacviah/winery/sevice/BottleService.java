package by.jacviah.winery.sevice;

import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Bottle;
import by.jacviah.winery.model.User;

import java.util.List;

public interface BottleService {
    public boolean addBottle(Bottle bottle) throws DaoException;
    public List<Bottle> getUserBottlesByPages(User user, int page);
}
