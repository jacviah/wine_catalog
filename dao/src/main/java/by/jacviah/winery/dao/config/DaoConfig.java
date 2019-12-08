package by.jacviah.winery.dao.config;

import by.jacviah.winery.dao.SommDAO;
import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.impl.DefaultSommDAO;
import by.jacviah.winery.dao.impl.DefaultUserDAO;
import by.jacviah.winery.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(HibernateConfig.class)
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "by.jacviah.winery.dao.repository")
public class DaoConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public UserDAO userDao() {
        return new DefaultUserDAO(userRepository);
    }

    @Bean
    public SommDAO sommDao() {
        return new DefaultSommDAO(userRepository);
    }

}
