package by.jacviah.winery.dao.repository;

import by.jacviah.winery.dao.entity.WineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WineRepository extends JpaRepository {
    public WineEntity findWineByNameAndWinery(String name, String winery);
}
