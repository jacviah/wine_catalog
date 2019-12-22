package by.jacviah.winery.dao.repository;

import by.jacviah.winery.dao.entity.RecommendEntity;
import by.jacviah.winery.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendRepository extends JpaRepository<RecommendEntity, Long> {
    public List<RecommendEntity> findAllByAuthorAndSubscriber(UserEntity author, UserEntity subscriber);
}
