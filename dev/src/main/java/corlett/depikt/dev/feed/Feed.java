package corlett.depikt.dev.feed;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corlett.depikt.dev.model.Image;
import corlett.depikt.dev.service.ImageServiceImpl;

//gets feed: list of image/description combinations
@Service
public class Feed {
    
    private final ImageServiceImpl imageService;
    
    private Long[] testImageIds;

    @Autowired
    public Feed(ImageServiceImpl imageService) {
        this.imageService = imageService;
        testImageIds = new Long[]{1L, 2L};
    }

    public List<Image> getFeed() {
        List<Image> feed = new ArrayList<>();
        //just select random ones for now
        //just for testing 
        for (Long imageId : testImageIds) {          
            feed.add(imageService.getImage(imageId));
        }
        
        return feed;
    }
    
}
