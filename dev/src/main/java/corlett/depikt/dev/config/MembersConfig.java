package corlett.depikt.dev.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import corlett.depikt.dev.service.DescriptionServiceImpl;
import corlett.depikt.dev.service.ImageServiceImpl;
import corlett.depikt.dev.service.MemberServiceImpl;
import java.time.LocalDate;

import corlett.depikt.dev.model.Description;
import corlett.depikt.dev.model.Image;
import corlett.depikt.dev.model.Member;
import corlett.depikt.dev.model.Role;

import java.util.ArrayList;

@Configuration
public class MembersConfig {

    @Bean
    CommandLineRunner commandLineRunner(MemberServiceImpl memberService, ImageServiceImpl imageService, DescriptionServiceImpl descriptionService) {
        return args -> {
            memberService.addRole(new Role(null, "ROLE_USER"));
            memberService.addRole(new Role(null, "ROLE_MANAGER"));
            memberService.addRole(new Role(null, "ROLE_ADMIN"));
            memberService.addRole(new Role(null, "ROLE_SUPER_ADMIN"));

            Member testUser1 = new Member("John", "Smith", "johnsmith@yahoo.com", "pwd", LocalDate.now(), new ArrayList<Role>());
            Member testUser2 = new Member("Billy", "Jones", "billyjones@hotmail.com", "pwd", LocalDate.now(), new ArrayList<Role>());
            Member testUser3 = new Member("Tim", "Davies", "timdavies@gmail.com", "pwd", LocalDate.now(), new ArrayList<Role>());
            Member testUser4 = new Member("Janet", "Collins", "janetcollins@hotmail.com", "pwd", LocalDate.now(), new ArrayList<Role>());
            memberService.addMember(testUser1);
            memberService.addMember(testUser2);
            memberService.addMember(testUser3);
            memberService.addMember(testUser4);

            memberService.addRoleToMember(testUser1.getId(), "ROLE_USER");
            memberService.addRoleToMember(testUser2.getId(), "ROLE_MANAGER");
            memberService.addRoleToMember(testUser3.getId(), "ROLE_ADMIN");
            memberService.addRoleToMember(testUser4.getId(), "ROLE_SUPER_ADMIN");
            memberService.addRoleToMember(testUser4.getId(), "ROLE_USER");

            Image hogwarts = new Image("hogwarts", "an image of the hogwarts school of witchcraft and wizardry from the Harry Potter series", "file:/Users/tomco/Documents/VSCode/Depikt/Depikt/dev/src/main/resources/static/feed-image/hogwarts.jpg");
            Image mordor = new Image("mordor", "an image of Mordor from the Lord of the Rings series", "file:/Users/tomco/Documents/VSCode/Depikt/Depikt/dev/src/main/resources/static/feed-image/mordor.jpg");
            Image theCastle = new Image("kafka_the_castle", "an interpretation of the castle from Kafka's unfinished novel 'The Castle'", "file:/Users/tomco/Documents/VSCode/Depikt/Depikt/dev/src/main/resources/static/feed-image/kafkathecastle.jpg");
            Image tyrellCorp = new Image("tyrell_corp", "A still from the film 'Blade Runner' depicting the Tyrell Corps main headquarters", "file:/Users/tomco/Documents/VSCode/Depikt/Depikt/dev/src/main/resources/static/feed-image/tyrellcorp.jpg");
            
            hogwarts = imageService.addImage(hogwarts);
            mordor = imageService.addImage(mordor);
            theCastle = imageService.addImage(theCastle);
            tyrellCorp = imageService.addImage(tyrellCorp);
        
            descriptionService.addDescription(new Description(hogwarts, testUser1.getId(), "The castle sat atop the hill looking all magic-y"));            
            descriptionService.addDescription(new Description(hogwarts, testUser4.getId(), "The spires and turrets of the castle grazed the sky as the train came sweeping around the corner"));
            descriptionService.addDescription(new Description(mordor, testUser1.getId(), "Mordor really weren't a nice place"));            
            descriptionService.addDescription(new Description(mordor, testUser4.getId(), "Mount Doom was aptly named"));            
            descriptionService.addDescription(new Description(mordor, testUser4.getId(), "Would have got here much faster if we just rode the eagles"));
        }; 
    }
    
}
