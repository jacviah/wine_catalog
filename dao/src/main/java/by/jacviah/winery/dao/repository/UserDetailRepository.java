package by.jacviah.winery.dao.repository;

import by.jacviah.winery.dao.entity.UserDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends CrudRepository<UserDetailEntity, Long> {
}
