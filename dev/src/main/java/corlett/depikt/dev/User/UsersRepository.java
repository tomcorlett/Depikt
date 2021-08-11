package corlett.depikt.dev.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    
    @Query("SELECT u FROM Users u WHERE u.username = ?1")
    Optional<Users> findUserByUsername(String username);
}
