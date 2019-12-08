package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.config.HibernateConfig;
import by.jacviah.winery.dao.entity.UserEntity;
import by.jacviah.winery.dao.repository.UserRepository;
import by.jacviah.winery.dao.util.mapper.UserMapper;
import by.jacviah.winery.model.Role;
import by.jacviah.winery.model.User;
import by.jacviah.winery.model.UserDetail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HibernateConfig.class)
public class SourceTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void jpaRepositoryTest() {
        User user = User.UserBuilder.anUser()
                .withUsername("gamer " + Instant.now().toString())
                .withPassword("1")
                .withRole(Role.USER)
                .withDetail(new UserDetail(null, "asdasdasd"))
                .build();
        UserEntity entity = UserMapper.toEntity(user);
        userRepository.save(entity);
        userRepository.delete(entity);
        userRepository.findById(entity.getId());
    }
}