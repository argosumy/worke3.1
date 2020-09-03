package main.java.project.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitilazer extends AbstractAnnotationConfigDispatcherServletInitializer implements WebApplicationInitializer {
    

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebConfig.class,RootConfig.class,SecurityConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {

        return new String[]{"/"};
    }
}
