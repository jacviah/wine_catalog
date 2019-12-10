package by.jacviah.winery.dao.repository;

import by.jacviah.winery.dao.entity.WineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineRepository extends JpaRepository<WineEntity, Long> {
    public WineEntity findWineByNameAndWinery(String name, String winery);
}
