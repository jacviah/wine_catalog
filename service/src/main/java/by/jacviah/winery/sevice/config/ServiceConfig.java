package by.jacviah.winery.sevice.config;

import by.jacviah.winery.dao.config.DaoConfig;
import by.jacviah.winery.sevice.BottleService;
import by.jacviah.winery.sevice.RecommendService;
import by.jacviah.winery.sevice.UserService;
import by.jacviah.winery.sevice.WineService;
import by.jacviah.winery.sevice.impl.DefaultBottleService;
import by.jacviah.winery.sevice.impl.DefaultRecommendService;
import by.jacviah.winery.sevice.impl.DefaultUserService;
import by.jacviah.winery.sevice.impl.DefaultWineService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

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
        return new DefaultBottleService(daoConfig.bottleDao());
    }

    @Bean
    public RecommendService recommendService(){
        return new DefaultRecommendService(daoConfig.recommendDao());
    }

}
