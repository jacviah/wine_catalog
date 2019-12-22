package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.RecommendDAO;
import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.model.Recommendation;
import by.jacviah.winery.model.User;
import by.jacviah.winery.sevice.RecommendService;

import java.util.List;

public class DefaultRecommendService implements RecommendService {

    private final RecommendDAO recommendDao;
    private final UserDAO userDao;

    public DefaultRecommendService(RecommendDAO recommendDao, UserDAO userDao) {
        this.recommendDao = recommendDao;
        this.userDao = userDao;
    }

    @Override
    public boolean createRecommendation(Recommendation rec) {
        return recommendDao.addRecommendation(rec);
    }

    @Override
    public List<Recommendation> findUsersRecommendation(Long id) {
        User user = userDao.findUser(id);
        User sommelier = userDao.getUsersSommelier(id);
        return recommendDao.findRecommendations(sommelier, user);
    }
}
