package corlett.depikt.dev.User;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Table
public class Users {
    @Id 
    @SequenceGenerator(
        name = "users_sequence",
        sequenceName = "users_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "users_sequence"
    )
    private Long id;
    private String forename;
    private String surname;
    private String username;
    private String email;
    private LocalDate dob;
    @Transient
    private Integer age;

    public Users() {}

    public Users(Long id, String forename, String surname, String username, String email, LocalDate dob) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.dob = dob;
    }

    public Users(String forename, String surname, String username, String email, LocalDate dob) {
        this.forename = forename;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.dob = dob;
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

    public void setForname(String forname) {
        this.forename = forname;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return this.username;
    }    

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return this.email;
    }  

    public void setEmail(String email) {
        this.email = email;
    }    

    public LocalDate getDob() {
        return this.dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
