package by.jacviah.winery.sevice;

import by.jacviah.winery.model.Recommendation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RecommendService {
    public boolean createRecommendation(Recommendation rec);
    public List<Recommendation> findUsersRecommendation(Long userId);
}
