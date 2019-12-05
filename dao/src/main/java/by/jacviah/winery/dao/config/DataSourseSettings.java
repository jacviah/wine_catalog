package by.jacviah.winery.dao.config;

import org.springframework.beans.factory.annotation.Value;

public class DataSourseSettings {


    @Value("${url}")
    private String url;

    @Value("${dbuser}")
    private String dbuser;

    @Value("${password}")
    private String password;

    @Value("${driver}")
    private String driver;

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return dbuser;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }

    @Override
    public String toString() {
        return "DataSourseSettings{" +
                "url='" + url + '\'' + "\n" +
                ", user='" + dbuser + '\'' + "\n" +
                ", password='" + password + '\'' + "\n" +
                ", driver='" + driver + '\'' + "\n" +
                '}';
    }
}
