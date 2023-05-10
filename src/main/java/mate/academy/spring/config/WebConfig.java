package mate.academy.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "mate.academy.spring.controller")

// тут ми будемо додавати якісь додаткові біни які нам треба буде для веб аплікейшина
public class WebConfig implements WebMvcConfigurer {
    // цей бін повертатиме InternalResource..., грубо кажучи
    // кажемо спрінгу де треба шукати файлики, які потрбно буде відображати
    // користувачу і тут кажемо що вони знаходимуться ось тут і будуть мати таке
    // розширення
    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
