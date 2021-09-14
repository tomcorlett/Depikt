package corlett.depikt.dev.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import corlett.depikt.dev.service.ImageServiceImpl;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @SequenceGenerator(
        name = "image_sequence",
        sequenceName = "image_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "image_sequence"
    )
    private Long imageId;
    private String imageName;
    private String altText;
    private String location;
    @OneToMany(mappedBy = "image")
    private List<Description> descriptions = new ArrayList<>();
    public Image(String imageName, String altText, String location) {
        this.imageName = imageName;
        this.altText = altText;
        this.location = location;
    }

    public void updateImage(Image image) {
        this.imageName = image.getImageName();
        this.altText = image.getAltText();
        this.location = image.getLocation();
    }

    @Override
    public String toString() {
        return "Image{" + 
        "\"id\":\"" + this.imageId + "\"," + 
        "\"imageName\":\"" + this.imageName + "\"," + 
        "\"altText\":\"" + this.altText + "\"," + 
        "\"location\":\"" + this.location + "\"" + 
        "}"; 
    }
}
