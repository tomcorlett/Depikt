package corlett.depikt.dev.repo;

import corlett.depikt.dev.model.Description;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptionRepo extends JpaRepository<Description, Long> {
    
    @Query(value = "SELECT d FROM DESCRIPTION d WHERE d.image_id = :imageId", nativeQuery = true)
    List<Description> findByImage_Id(@Param("imageId") Long imageId);

}
