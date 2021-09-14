package corlett.depikt.dev.service;

import java.util.List;

import corlett.depikt.dev.model.Description;

public interface DescriptionService {
    Description getDescription(Long descriptionId);
    List<Description> getDescriptionsByImageId(Long imageId);
    Description getDescription();
    void addDescription(Description description);
    void updateDescription(Long descriptionId, Description description);
    void deleteDescription(Long descriptionId);
}


