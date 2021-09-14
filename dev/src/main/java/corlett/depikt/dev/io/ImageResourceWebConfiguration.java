package corlett.depikt.dev.io;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration @EnableWebMvc
public class ImageResourceWebConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        System.out.println("configuring imageResourceWebConfig");
        registry.addResourceHandler("/images/**").addResourceLocations("file:/Users/tomco/Documents/VSCode/Depikt/Depikt/dev/src/main/resources/static/feed-image/");
    }
}