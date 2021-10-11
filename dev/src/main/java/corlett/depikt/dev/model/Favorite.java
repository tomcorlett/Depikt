package corlett.depikt.dev.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Favorite implements Serializable {
    @Id
    @SequenceGenerator(
        name = "favorite_sequence",
        sequenceName = "favorite_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "favorite_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "description_id")
    private Description description;

    private LocalDateTime createdAt;    

    public Favorite(Member member, Description description, LocalDateTime createdAt) {
        this.member = member;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Favorite(Member member, Description description) {
        this.member = member;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }
}
