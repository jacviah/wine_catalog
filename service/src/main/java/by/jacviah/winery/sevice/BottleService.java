package by.jacviah.winery.sevice;

import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Bottle;
import by.jacviah.winery.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BottleService {
    public boolean addBottle(Bottle bottle);
    public List<Bottle> getUserBottlesByPages(User user, Pageable page);
    public boolean deleteBottle(Long id);
}
