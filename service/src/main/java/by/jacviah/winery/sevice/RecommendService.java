package by.jacviah.winery.sevice;

import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Recommendation;

public interface RecommendService {
    public boolean createRecommendation(Recommendation rec);
}
