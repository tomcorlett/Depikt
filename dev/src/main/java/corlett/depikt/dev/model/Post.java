package corlett.depikt.dev.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Post {
    private Image image;
    private List<Description> descriptions;
}
