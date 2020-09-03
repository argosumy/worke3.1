package main.java.project.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class DataSourseMy {
    private String nameJNDI;
    private String url;
    private String factory;


    public DataSourseMy(@Value("${data.JNDIname}")String nameJNDI,
                        @Value("${url}")String url,
                        @Value("${factory}")String factory){
        this.nameJNDI = nameJNDI;
        this.url = url;
        this.factory = factory;
    }

    public String getNameJNDI() {
        return nameJNDI;
    }

    public void setNameJNDI(String nameJNDI) {
        this.nameJNDI = nameJNDI;
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
