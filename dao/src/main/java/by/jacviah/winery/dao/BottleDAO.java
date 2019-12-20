package by.jacviah.winery.dao;

import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Bottle;
import by.jacviah.winery.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BottleDAO {
        public boolean addBottle(Bottle bottle);
        public boolean removeBottle(Long id);
        public List<Bottle> getUserBottles(User user, Pageable pageable);
}
