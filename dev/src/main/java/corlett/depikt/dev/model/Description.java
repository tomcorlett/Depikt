package corlett.depikt.dev.model;
import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import corlett.depikt.dev.service.MemberServiceImpl;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Description {

    public Description(Image image, Member member, String description) {
        this.image = image;
        this.member = member;
        this.description = description;
    }

    @Id
    @SequenceGenerator(
        name = "description_sequence",
        sequenceName = "description_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "description_sequence"
    )
    private Long id;
    @Transient
    private Long imageId;
    @ManyToOne
    @JoinColumn(name="image_id", nullable=false)
    @JsonBackReference
    private Image image;
    //https://stackoverflow.com/questions/1281952/what-is-the-easiest-way-to-ignore-a-jpa-field-during-persistence/41850392#41850392
    //If you need mix JPA with JSON(omit by JPA but still include in Jackson) use @JsonInclude :
    //@JsonInclude()    
    @OneToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;
    private String description;    

    public void setMember(Member member) {
        System.out.println("in Description.setMember :: " + member.toString());
        this.member = member;
    }
}
