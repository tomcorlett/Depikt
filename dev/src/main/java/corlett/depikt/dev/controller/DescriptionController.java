package corlett.depikt.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corlett.depikt.dev.model.Description;
import corlett.depikt.dev.service.DescriptionServiceImpl;


@RestController
@RequestMapping(path = "api/v1/description")
public class DescriptionController {
    private final DescriptionServiceImpl descriptionService;

    @Autowired
    public DescriptionController(DescriptionServiceImpl descriptionService) {
        this.descriptionService = descriptionService;
    }

    @GetMapping(path = "{descriptionId}")
    public Description getDescription(@PathVariable("descriptionId") Long descriptionId) {
        return descriptionService.getDescription(descriptionId);
    }

    @PostMapping
    public void addDescription(@RequestBody Description description) {
        descriptionService.addDescription(description);
    }    
}
