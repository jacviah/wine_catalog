package by.jacviah.winery.dao;

import by.jacviah.winery.model.Recommendation;
import by.jacviah.winery.model.User;

import java.util.List;

public interface RecommendDAO {

    public boolean addRecommendation(Recommendation rec);
    public List<Recommendation> findRecommendations(User author, User subscriber);
}
