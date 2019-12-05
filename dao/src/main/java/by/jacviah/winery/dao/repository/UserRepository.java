package by.jacviah.winery.dao.repository;

import java.util.List;

import by.jacviah.winery.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import by.jacviah.winery.dao.entity.CountryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String name);
}

