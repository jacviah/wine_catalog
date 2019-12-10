package by.jacviah.winery.dao.repository;

import by.jacviah.winery.dao.entity.CountryEntity;
import by.jacviah.winery.dao.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<CountryEntity, Long> {
    CountryEntity findByName(String name);
}
