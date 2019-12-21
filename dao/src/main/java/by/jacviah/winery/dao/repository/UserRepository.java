package by.jacviah.winery.dao.repository;

import by.jacviah.winery.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String name);
    List<UserEntity> getByRole(String role);
    List<UserEntity> getBySommelier(UserEntity sommelier);
}

