package webapp.modelDAO.repos;

import org.springframework.data.repository.CrudRepository;
import webapp.model.entities.Client;

public interface ClientRepo extends CrudRepository<Client, Long> {
}
