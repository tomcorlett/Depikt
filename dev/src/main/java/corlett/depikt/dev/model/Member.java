package corlett.depikt.dev.model;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.SequenceGenerator;
import static javax.persistence.FetchType.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Member {
    @Id
    @SequenceGenerator(
        name = "member_sequence",
        sequenceName = "member_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "member_sequence"
    )
    private Long id;
    private String forename;
    private String surname;
    private String email;
    private String password;
    private LocalDate dob;

    @ManyToMany(fetch = EAGER)
    private Set<Role> roles = new HashSet<>();


    public Member() {}

    public Member(Long id, String forename, String surname, String email, String password, LocalDate dob, Set<Role> roles) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.roles = roles;
    }

    public Member(String forename, String surname, String email, String password, LocalDate dob, Set<Role> roles) {
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.roles = roles;
    }

    //used by CustomAuthenticationFilter
    public Member(String email, String password) {
        this.email = email;
        this.setPasswordUsingEncryption(password);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getForename() {
        return this.forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordUsingEncryption(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.password = bCryptPasswordEncoder.encode(password);
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Member{" + 
            "\"id\":\"" + this.id + "\"," + 
            "\"forename\":\"" + this.forename + "\"," + 
            "\"surname\":\"" + this.surname + "\"," + 
            "\"email\":\"" + this.email + "\"," +  
            "\"password\":\"" + this.password + "\"," + 
            "\"dob\":\"" + this.dob + "\"" + 
            "}"; 
    }

    public void updateMember(Member member) {
        if (member.getForename() != null && member.getForename().length() > 0 && !member.getForename().equals(this.forename)) {
            this.setForename(member.getForename());
        }

        if (member.getSurname() != null && member.getSurname().length() > 0 && !member.getSurname().equals(this.surname)) {
            this.setSurname(member.getSurname());
        }

        if (member.getEmail() != null && member.getEmail().length() > 0 && !member.getEmail().equals(this.email)) {
            this.setEmail(member.getEmail());
        }

        if (member.getDob() != null && member.getDob() != this.dob){
            this.setDob(member.getDob());
        }
    }

    
}
