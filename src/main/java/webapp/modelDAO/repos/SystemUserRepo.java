package webapp.modelDAO.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webapp.model.entities.SystemUser;

import java.util.Optional;

@Repository
public interface SystemUserRepo extends JpaRepository<SystemUser, Long> {
    Optional<SystemUser> findByUsername(String username);
}
