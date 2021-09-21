package corlett.depikt.dev.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Image {

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
    private Long id;
    private String imageName;
    private String altText;
    private String location;
    @OneToMany(
        mappedBy = "image",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JsonManagedReference
    private List<Description> descriptions = new ArrayList<>();
    
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
