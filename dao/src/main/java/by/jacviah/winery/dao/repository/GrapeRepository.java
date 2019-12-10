package by.jacviah.winery.dao.repository;

import by.jacviah.winery.dao.entity.GrapeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrapeRepository extends CrudRepository<GrapeEntity, Long> {
}
