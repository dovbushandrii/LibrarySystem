package webapp.modelDAO.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import webapp.model.entities.Item;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item, Long> {

    @Query("select it from Item it where it.id not in (select itm.id from Loan l join l.items itm)")
    List<Item> findAllAvailable();
}
