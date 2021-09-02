package webapp.modelDAO.daos;

import org.springframework.beans.factory.annotation.Autowired;
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
                .stream(repo.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    public Loan read(long id) {
        return repo.findById(id)
                .get();
    }

    public void update(Loan loan, long id) {
        Loan loanToBeUpdated = read(id);
        loanToBeUpdated.setEndDate(loan.getEndDate());
        loanToBeUpdated.setStatus(loan.getStatus());
    }

    public void delete(Loan loan) {
        repo.delete(loan);
    }

    public void delete() {
        repo.deleteAll();
    }
}
