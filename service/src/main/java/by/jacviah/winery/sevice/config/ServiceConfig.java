package by.jacviah.winery.sevice.config;

import by.jacviah.winery.dao.config.DaoConfig;
import by.jacviah.winery.sevice.*;
import by.jacviah.winery.sevice.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Autowired
    private DaoConfig daoConfig;

    public ServiceConfig(DaoConfig daoConfig) {
        this.daoConfig = daoConfig;
    }

    @Bean
    public UserService userService(){
        return new DefaultUserService(daoConfig.userDao());
    }

    @Bean
    public WineService wineService(){
        return new DefaultWineService(daoConfig.wineDao(), daoConfig.metaDataDAO());
    }

    @Bean
    public BottleService bottleService(){
        return new DefaultBottleService(daoConfig.bottleDao(), daoConfig.wineDao());
    }

    @Bean
    public RecommendService recommendService(){
        return new DefaultRecommendService(daoConfig.recommendDao(), daoConfig.userDao());
    }

    @Bean
    public MetadataService metadataService(){
        return new DefaultMetadataService(daoConfig.metaDataDAO());
    }

    @Bean
    public SecurityService securityService(){
        return new DefaultSecurityService(daoConfig.userDao());
    }

}
