package project.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataSourse {
    private String sourceName;
    private String connectionUrl;
    private String driverClass;
    private String userName;
    private String password;

    public DataSourse(@Value("${sourse.name}") String sourceName, @Value("${connection.url}") String connectionUrl,
                      @Value("${driver.class}") String driverClass, @Value("${name}") String userName,
                      @Value("${password}")String password) {
        this.sourceName = sourceName;
        this.connectionUrl = connectionUrl;
        this.driverClass = driverClass;
        this.userName = userName;
        this.password = password;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
