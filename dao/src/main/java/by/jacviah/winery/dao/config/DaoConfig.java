package by.jacviah.winery.dao.config;

import by.jacviah.winery.dao.BottleDAO;
import by.jacviah.winery.dao.SommDAO;
import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.WineDAO;
import by.jacviah.winery.dao.impl.DefaultBottleDAO;
import by.jacviah.winery.dao.impl.DefaultSommDAO;
import by.jacviah.winery.dao.impl.DefaultUserDAO;
import by.jacviah.winery.dao.impl.DefaultWineDAO;
import by.jacviah.winery.dao.repository.BottleRepository;
import by.jacviah.winery.dao.repository.UserRepository;
import by.jacviah.winery.dao.repository.WineRepository;
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
    @Autowired
    private WineRepository wineRepository;
    @Autowired
    private BottleRepository bottleRepository;

    @Bean
    public UserDAO userDao() {
        return new DefaultUserDAO(userRepository);
    }

    @Bean
    public SommDAO sommDao() {
        return new DefaultSommDAO(userRepository);
    }
    @Bean
    public WineDAO wineDao() {
        return new DefaultWineDAO(wineRepository);
    }

    @Bean
    public BottleDAO bottleDao() {
        return new DefaultBottleDAO(bottleRepository);
    }
}
