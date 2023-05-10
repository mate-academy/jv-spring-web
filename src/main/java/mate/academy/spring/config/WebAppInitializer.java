package mate.academy.spring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        // вказуємо конфігураційний основний рутовий клас
        return new Class[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // тут вказується клас для конфігурації веб аплікейшина
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        // тут вказуємо дефолтний маппінг для всіх нашиї контролерів
        return new String[]{"/"};
    }
}
