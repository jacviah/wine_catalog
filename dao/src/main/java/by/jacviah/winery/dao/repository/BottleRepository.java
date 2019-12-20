package by.jacviah.winery.dao.repository;

import by.jacviah.winery.dao.entity.BottleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BottleRepository extends JpaRepository<BottleEntity, Long> {
    @Query("select b from BottleEntity as b inner join b.user as u where u.id = :user")
    List<BottleEntity> getUserBottles(@Param("user") Long id, Pageable pageable);

    @Modifying
    @Query("delete from BottleEntity b where b.id=:id")
    void deleteBottle (@Param("id")Long id);

    @Query("select count(b) from BottleEntity as b inner join b.user as u where u.id = :user")
    Integer countUserBottles(@Param("user") Long id);
}
