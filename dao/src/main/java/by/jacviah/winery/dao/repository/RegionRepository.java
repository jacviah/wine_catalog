package by.jacviah.winery.dao.repository;

import by.jacviah.winery.dao.entity.RegionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends CrudRepository<RegionEntity, Long> {
    @Query("select r from CountryEntity c join c.regions r where c.name = :name")
    List<RegionEntity> getCountryRegions(@Param("name") String name);
}

