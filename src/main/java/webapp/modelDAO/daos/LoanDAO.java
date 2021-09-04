/**
 * @file LoanDAO.java
 * @brief This file contains Loan entities DAO
 *
 * @author Andrii Dovbush
 */

package webapp.modelDAO.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import webapp.model.entities.Loan;
import webapp.modelDAO.repos.LoanRepo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class LoanDAO {

    private final LoanRepo repo;

    @Autowired
    public LoanDAO(LoanRepo repo) {
        this.repo = repo;
    }

    public void create(Loan loan) {
        repo.save(loan);
    }

    public List<Loan> read() {
        return StreamSupport
                .stream(repo.findAll(Sort.by(Sort.Direction.DESC,"endDate")).spliterator(), false)
                .collect(Collectors.toList());
    }

    public Loan read(long id) {
        return repo.findById(id)
                .get();
    }

    public void update(Loan loan, long id) {
        Loan loanToBeUpdated = read(id);
        loanToBeUpdated.setStartDate(loan.getStartDate());
        loanToBeUpdated.setEndDate(loan.getEndDate());
        loanToBeUpdated.setClient(loan.getClient());
        loanToBeUpdated.setItems(loan.getItems());
        repo.save(loanToBeUpdated);
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
