package project.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class DataSourse {
    private String dataSourse;
    private String url;
    private String factory;


    public DataSourse(@Value("${data.JNDIname}")String dataSourse,
                      @Value("${url}")String url,
                      @Value("${factory}")String factory)
    {
        this.dataSourse = dataSourse;
        this.url = url;
        this.factory = factory;
    }

    public String getDataSourse() {
        return dataSourse;
    }

    public void setDataSourse(String dataSourse) {
        this.dataSourse = dataSourse;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }
}
