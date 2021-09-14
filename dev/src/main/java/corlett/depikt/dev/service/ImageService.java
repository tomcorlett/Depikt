package corlett.depikt.dev.service;

import java.util.List;

import corlett.depikt.dev.model.Image;

public interface ImageService {
    Image getImage(Long imageId);
    List<Image> getImages();
    void addImage(Image image);
    void updateImage(Long imageId, Image image);
    void deleteImage(Long imageId);
}
