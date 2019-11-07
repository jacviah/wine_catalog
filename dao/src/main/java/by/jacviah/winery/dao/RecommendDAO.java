package by.jacviah.winery.dao;

import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Recommendation;

public interface RecommendDAO {

    public boolean addRecommendation(Recommendation rec) throws DaoException;
}
