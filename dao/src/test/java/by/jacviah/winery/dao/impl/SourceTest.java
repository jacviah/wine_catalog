package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.config.HibernateConfig;
import by.jacviah.winery.dao.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HibernateConfig.class)
public class SourceTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void jpaRepositoryTest() {
        System.out.println(userRepository.findByUsername("user"));
    }
}