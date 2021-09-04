/**
 * @file LoanRepo.java
 * @brief This file contains Loan repository interface
 *
 * @author Andrii Dovbush
 */

package webapp.modelDAO.repos;

import org.springframework.data.repository.CrudRepository;
import webapp.model.entities.Loan;

public interface LoanRepo extends CrudRepository<Loan, Long> {
}
