package by.jacviah.winery.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSource {

    private final ComboPooledDataSource pool;
    private static final Logger log = LoggerFactory.getLogger(DataSource.class);
    public DataSource() {
        pool = new ComboPooledDataSource();
        ResourceBundle resource = ResourceBundle.getBundle("db");
        try {
            Class.forName(resource.getString("driver"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("исправь эту обработку");
        }
        String url = resource.getString("url");
        log.trace("url:{}", url);
        String user = resource.getString("user");
        String pass = resource.getString("password");
        pool.setJdbcUrl(url);
        pool.setUser(user);
        pool.setPassword(pass);

        pool.setMinPoolSize(5);
        pool.setAcquireIncrement(5);
        pool.setMaxPoolSize(10);
        pool.setMaxStatements(180);
    }

    private static class SingletonHolder {
        static final DataSource HOLDER_INSTANCE = new DataSource();
    }

    public static DataSource getInstance() {
        return DataSource.SingletonHolder.HOLDER_INSTANCE;
    }

    public Connection getConnection() {
        try {
            return this.pool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

