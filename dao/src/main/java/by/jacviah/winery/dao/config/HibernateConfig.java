package by.jacviah.winery.dao.config;

import by.jacviah.winery.dao.impl.DefaultUserDAO;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
@Import(SettingsConfig.class)
@EnableJpaRepositories(basePackages = "by.jacviah.winery.dao.repository")
public class HibernateConfig {
    private static final Logger log = LoggerFactory.getLogger(HibernateConfig.class);

    private final SettingsConfig settingsConfig;

    public HibernateConfig(SettingsConfig settingsConfig) {
        this.settingsConfig = settingsConfig;
    }

    @Bean
    public DataSource dataSource() {
        final DataSourseSettings dataSourseSettings = settingsConfig.dataSourseSettings();
        final HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(dataSourseSettings.getUrl());
        hikariDataSource.setUsername(dataSourseSettings.getUser());
        hikariDataSource.setPassword(dataSourseSettings.getPassword());
        hikariDataSource.setDriverClassName(dataSourseSettings.getDriver());
        hikariDataSource.setMaximumPoolSize(20);
        return hikariDataSource;
    }

    @Bean
    public LocalSessionFactoryBean entityManagerFactory() {
        final LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
        sf.setDataSource(dataSource());
        sf.setPackagesToScan("by.jacviah.winery.dao.entity");
        sf.setHibernateProperties(settingsConfig.hibernateProperties());

        return sf;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

}
