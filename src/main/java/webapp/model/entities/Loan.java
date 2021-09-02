package webapp.model.entities;

import lombok.Getter;
import lombok.Setter;
import webapp.model.enums.LoanStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Loan {
    @Id
    @GeneratedValue
    private long id;

    @Getter
    private final LocalDate startDate;

    @Getter
    @Setter
    private LocalDate endDate;

    @Getter
    @Setter
    private LoanStatus status;

    @ManyToOne
    @Getter
    private final Item item;

    @ManyToOne
    @Getter
    private final Client client;

    public Loan(){
        this.client = null;
        this.item = null;
        this.status = LoanStatus.NOT_DEF;
        this.startDate = null;
        this.endDate = null;
    }

    public Loan(LocalDate startDate,
                LocalDate endDate,
                Item item,
                Client client) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = LoanStatus.NEW;
        this.item = item;
        this.client = client;
    }
}
