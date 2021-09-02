package webapp.modelDAO.repos;

import org.springframework.data.repository.CrudRepository;
import webapp.model.entities.Item;

public interface ItemRepo extends CrudRepository<Item, Long> {
}
