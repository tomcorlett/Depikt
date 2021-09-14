package corlett.depikt.dev.feed;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import corlett.depikt.dev.model.Post;
import corlett.depikt.dev.service.DescriptionServiceImpl;
import corlett.depikt.dev.service.ImageServiceImpl;

//gets feed: list of image/description combinations
public class Feed {
    private List<Post> feed;
    private final ImageServiceImpl imageService;
    private final DescriptionServiceImpl descriptionService;
    
    private Long[] testImageIds;

    @Autowired
    public Feed(ImageServiceImpl imageService, DescriptionServiceImpl descriptionService) {
        this.imageService = imageService;
        this.descriptionService = descriptionService;
        testImageIds = new Long[]{9L, 10L};
        feed = new ArrayList<>();
    }

    public List<Post> getFeed() {
        //just select random ones for now
        //just for testing 
        for (Long imageId : testImageIds) {
            Post post = new Post();
            post.setImage(imageService.getImage(imageId));
            post.setDescriptions(descriptionService.getDescriptionsByImageId(imageId));
            feed.add(post);
        }
        
        return feed;
    }
    
}
