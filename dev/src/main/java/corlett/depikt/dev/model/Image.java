package corlett.depikt.dev.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Table
@Data
public class Image {
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
    private String imageName;
    private String altText;
    private String location;

    public void updateImage(Image image) {
        this.imageName = image.getImageName();
        this.altText = image.getAltText();
        this.location = image.getLocation();
    }

    @Override
    public String toString() {
        return "Image{" + 
        "\"id\":\"" + this.id + "\"," + 
        "\"imageName\":\"" + this.imageName + "\"," + 
        "\"altText\":\"" + this.altText + "\"," + 
        "\"location\":\"" + this.location + "\"" + 
        "}"; 
    }
}
