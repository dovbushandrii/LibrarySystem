package webapp.modelDAO.repos;

import org.springframework.data.repository.CrudRepository;
import webapp.model.entities.Loan;

public interface LoanRepo extends CrudRepository<Loan, Long> {
}
