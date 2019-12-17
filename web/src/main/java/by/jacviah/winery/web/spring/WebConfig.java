package by.jacviah.winery.web.spring;


import by.jacviah.winery.sevice.config.ServiceConfig;
import by.jacviah.winery.web.controller.LoginController;
import by.jacviah.winery.web.controller.LogoutController;
import by.jacviah.winery.web.controller.UserController;
import by.jacviah.winery.web.controller.WelcomeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {
        "by.jacviah.winery.web"})
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ServiceConfig serviceConfig;

    public WebConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    @Bean
    public LoginController loginController(){
        return new LoginController(serviceConfig.securityService());
    }

    @Bean
    public LogoutController logoutController(){
        return new LogoutController(serviceConfig.securityService());
    }

    @Bean
    public UserController userController(){
        return new UserController(serviceConfig.userService());
    }
    @Bean
    public WelcomeController welcomeController(){
        return new WelcomeController();
    }

    @Bean
    public UrlBasedViewResolver tilesViewResolver(){
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setViewClass(TilesView.class);
        return resolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer(){
        final TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions("/WEB-INF/tiles.xml");
        return tilesConfigurer;
    }
}
