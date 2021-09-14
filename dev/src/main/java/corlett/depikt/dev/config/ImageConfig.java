package corlett.depikt.dev.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import corlett.depikt.dev.service.ImageServiceImpl;

import corlett.depikt.dev.model.Image;

@Configuration
@ComponentScan
public class ImageConfig {

    @Bean
    CommandLineRunner commandLineRunner(ImageServiceImpl imageService) {
        System.out.println("in imageConfig");
        return args -> {
            imageService.addImage(new Image("hogwarts", "an image of the hogwarts school of witchcraft and wizardry from the Harry Potter series", "file:/Users/tomco/Documents/VSCode/Depikt/Depikt/dev/src/main/resources/static/feed-image/hogwarts.jpg"));
        }; 
    }
    
}
