package webapp.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Loan {
    @Id
    @GeneratedValue
    private long id;

    @Getter
    @Setter
    private final LocalDate startDate;

    @Getter
    @Setter
    private LocalDate endDate;

    @ManyToOne
    @Getter
    @Setter
    private Item item;

    @ManyToOne
    @Getter
    @Setter
    private Client client;

    public Loan(){
        this.client = null;
        this.item = null;
        this.startDate = null;
        this.endDate = null;
    }

    public Loan(LocalDate startDate,
                LocalDate endDate,
                Item item,
                Client client) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.item = item;
        this.client = client;
    }
}
