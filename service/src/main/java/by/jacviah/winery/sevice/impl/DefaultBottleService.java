package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.BottleDAO;
import by.jacviah.winery.dao.DAOProvider;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Bottle;
import by.jacviah.winery.model.User;
import by.jacviah.winery.sevice.BottleService;

import java.util.List;

public class DefaultBottleService implements BottleService {

    private DAOProvider provider = DAOProvider.getInstance();
    private BottleDAO bottleDAO = provider.getBottleDAO();

    @Override
    public boolean addBottle(Bottle bottle) throws DaoException {
        return bottleDAO.addBottle(bottle);
    }

    @Override
    public List<Bottle> getUserBottlesByPages(User user, int page) {
        return bottleDAO.getUserBottles(user, page);
    }
}
