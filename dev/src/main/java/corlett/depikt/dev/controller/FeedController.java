package corlett.depikt.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corlett.depikt.dev.feed.Feed;
import corlett.depikt.dev.model.Image;

@RestController
@RequestMapping(path = "api/v1/feed")
public class FeedController {
    private final Feed feed;

    @Autowired
    public FeedController(Feed feed) {
        this.feed = feed;
    }
    
    @GetMapping
    public List<Image> getFeed() {
        return feed.getFeed();
    }
}
