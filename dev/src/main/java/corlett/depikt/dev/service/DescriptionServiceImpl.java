package corlett.depikt.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corlett.depikt.dev.model.Description;
import corlett.depikt.dev.repo.DescriptionRepo;

@Service
public class DescriptionServiceImpl implements DescriptionService {
    private final DescriptionRepo descriptionRepo;

    @Autowired
    public DescriptionServiceImpl(DescriptionRepo descriptionRepo) {
        this.descriptionRepo = descriptionRepo;
    }

    @Override
    public Description getDescription(Long descriptionId) {
        return descriptionRepo.getById(descriptionId);
    }

    @Override
    public List<Description> getDescriptionsByImageId(Long imageId) {
       return descriptionRepo.findByImageId(imageId);
    }

    @Override
    public Description getDescription() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addDescription(Description description) {
        descriptionRepo.save(description);
    }

    @Override
    public void updateDescription(Long descriptionId, Description description) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteDescription(Long descriptionId) {
        // TODO Auto-generated method stub
        
    }
    
}
