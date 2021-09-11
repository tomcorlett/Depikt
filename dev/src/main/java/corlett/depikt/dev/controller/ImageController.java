package corlett.depikt.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corlett.depikt.dev.model.Image;
import corlett.depikt.dev.service.ImageServiceImpl;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/image")
public class ImageController {
    private final ImageServiceImpl imageService;

    @Autowired
    public ImageController(ImageServiceImpl imageService) {
        this.imageService = imageService;
    }

    @GetMapping(path = "{imageId}")
    public Image getImage(@PathVariable("imageId") Long imageId) {
        return imageService.getImage(imageId);
    }

    @GetMapping
    public List<Image> getImages() {
        System.out.println("get images");
        return imageService.getImages();
    }

    @PostMapping
    public void addImage(@RequestBody Image image) {
        System.out.println("in add Image ");
        imageService.addImage(image);
    }

    @PutMapping(path = "{imageId}")
    public void updateImage(@PathVariable("imageId") Long imageId, @RequestBody Image image) {
        imageService.updateImage(imageId, image);
    }

    @DeleteMapping(path = "{imageId}")
    public void deleteImage(@PathVariable("imageId") Long imageId) {
        imageService.deleteImage(imageId);
    }
}
