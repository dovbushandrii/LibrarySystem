/**
 * @file ItemRepo.java
 * @brief This file contains Item repository interface
 *
 * @author Andrii Dovbush
 */

package webapp.modelDAO.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import webapp.model.entities.Item;

import java.util.List;

public interface ItemRepo extends CrudRepository<Item, Long> {

    /**
     * Selects all items that are not in loan
     * @return List of available items
     */
    @Query("select it from Item it where it.id not in (select itm.id from Loan l join l.items itm)")
    List<Item> findAllAvailable();
}
