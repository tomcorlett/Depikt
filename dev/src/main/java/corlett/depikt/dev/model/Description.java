package corlett.depikt.dev.model;
import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import static javax.persistence.FetchType.*;

import java.util.HashSet;
import java.util.Set;

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

    public Description(Image image, Member member, String description, String title) {
        this.image = image;
        this.member = member;
        this.description = description;
        this.title = title;
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
    private String title;
    @ManyToOne
    @JoinColumn(name="image_id", nullable=false)
    @JsonBackReference
    private Image image;   
    @OneToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;
    private String description; 
    @OneToMany(mappedBy = "description")
    Set<Favorite> favorites;
}
