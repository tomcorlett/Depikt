package corlett.depikt.dev.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@Data
public class Description {
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
    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;
    //https://stackoverflow.com/questions/1281952/what-is-the-easiest-way-to-ignore-a-jpa-field-during-persistence/41850392#41850392
    //If you need mix JPA with JSON(omit by JPA but still include in Jackson) use @JsonInclude :
    //@JsonInclude()
    @Transient
    private Long imageId;
    private Long memberId;
    private String description;    
}
