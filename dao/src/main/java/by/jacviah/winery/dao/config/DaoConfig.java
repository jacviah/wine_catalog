package by.jacviah.winery.dao.config;

import by.jacviah.winery.dao.*;
import by.jacviah.winery.dao.impl.*;
import by.jacviah.winery.dao.repository.*;
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
    private UserDetailRepository userDetailRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private GrapeRepository grapeRepository;
    @Autowired
    private WineRepository wineRepository;
    @Autowired
    private BottleRepository bottleRepository;
    @Autowired
    private RecommendRepository recommendRepository;

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

    @Bean
    public RecommendDAO recommendDao() {
        return new DefaultRecommendDAO(recommendRepository);
    }

    @Bean
    public MetaDataDAO metaDataDAO() {
       return new DefaultMetaDataDAO(countryRepository, regionRepository, grapeRepository);
    }
}
