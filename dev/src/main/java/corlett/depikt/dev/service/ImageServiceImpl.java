package corlett.depikt.dev.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corlett.depikt.dev.model.Image;
import corlett.depikt.dev.repo.ImageRepo;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepo imageRepo;

    @Autowired
    public ImageServiceImpl(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    @Override
    public Image getImage(Long imageId) {
        return imageRepo.findById(imageId).get();
        
    }

    @Override
    public List<Image> getImages() {
        return imageRepo.findAll();
    }

    @Override
    public void addImage(Image image) {
        imageRepo.save(image);        
    }

    @Override
    @Transactional
    public void updateImage(Long imageId, Image image) {
        Image imageFromId = imageRepo.findById(imageId).orElseThrow(() -> new IllegalStateException("Image with ID " + imageId + " does not exist"));
        imageFromId.updateImage(image);
    }

    @Override
    public void deleteImage(Long imageId) {
        imageRepo.deleteById(imageId);        
    }
    
}
