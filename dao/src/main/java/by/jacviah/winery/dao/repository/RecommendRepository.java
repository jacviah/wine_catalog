package by.jacviah.winery.dao.repository;

import by.jacviah.winery.dao.entity.RecommendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendRepository extends JpaRepository<RecommendEntity, Long> {
}
