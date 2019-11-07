package by.jacviah.winery.dao;

import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Bottle;

public interface BottleDAO {
        public boolean addBottle(Bottle bottle) throws DaoException;
}
