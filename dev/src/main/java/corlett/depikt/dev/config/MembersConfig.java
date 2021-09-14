package corlett.depikt.dev.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import corlett.depikt.dev.service.ImageServiceImpl;
import corlett.depikt.dev.service.MemberServiceImpl;
import java.time.LocalDate;

import corlett.depikt.dev.model.Image;
import corlett.depikt.dev.model.Member;
import corlett.depikt.dev.model.Role;

import java.util.ArrayList;

@Configuration
public class MembersConfig {

    @Bean
    CommandLineRunner commandLineRunner(MemberServiceImpl memberService, ImageServiceImpl imageService) {
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

            imageService.addImage(new Image("hogwarts", "an image of the hogwarts school of witchcraft and wizardry from the Harry Potter series", "file:/Users/tomco/Documents/VSCode/Depikt/Depikt/dev/src/main/resources/static/feed-image/hogwarts.jpg"));
      
        }; 
    }
    
}
