package corlett.depikt.dev.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import corlett.depikt.dev.model.Member;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {

    Member findByEmail(String email);

}
