package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.RecommendDAO;
import by.jacviah.winery.model.Recommendation;
import by.jacviah.winery.sevice.RecommendService;

public class DefaultRecommendService implements RecommendService {

    private final RecommendDAO recommendDao;

    public DefaultRecommendService(RecommendDAO recommendDao) {
        this.recommendDao = recommendDao;
    }

    @Override
    public boolean createRecommendation(Recommendation rec) {
        return recommendDao.addRecommendation(rec);
    }
}
