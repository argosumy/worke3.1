package project.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class DataSourse {
    private String sourceName;
    private String connectionUrl;
    private String driverClass;
    private String userName;
    private String password;
    private int initialPoolSize;
    private int acquireIncrement;
    private int maxPoolSize;
    private int minPoolSize;


    public DataSourse(@Value("${sourse.name}") String sourceName,
                      @Value("${connection.url}") String connectionUrl,
                      @Value("${driver.class}") String driverClass,
                      @Value("${name}") String userName,
                      @Value("${password}")String password,
                      @Value("${initial.pool.size}")int poolSize,
                      @Value("${acquireIncrement}") int acquireIncrement,
                      @Value("${maxPoolSize}") int maxPoolSize,
                      @Value("${minPoolSize}") int minPoolSize) {
        this.sourceName = sourceName;
        this.connectionUrl = connectionUrl;
        this.driverClass = driverClass;
        this.userName = userName;
        this.password = password;
        this.initialPoolSize = poolSize;
        this.acquireIncrement = acquireIncrement;
        this.maxPoolSize = maxPoolSize;
        this.minPoolSize = minPoolSize;
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

    public int getInitialPoolSize() {
        return initialPoolSize;
    }

    public void setInitialPoolSize(int initialPoolSize) {
        this.initialPoolSize = initialPoolSize;
    }

    public int getAcquireIncrement() {
        return acquireIncrement;
    }

    public void setAcquireIncrement(int acquireIncrement) {
        this.acquireIncrement = acquireIncrement;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getMinPoolSize() {
        return minPoolSize;
    }

    public void setMinPoolSize(int minPoolSize) {
        this.minPoolSize = minPoolSize;
    }
}
