package webapp.model.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Loan {
    @Id
    @GeneratedValue
    @Getter
    private long id;

    @Getter
    @Setter
    @PastOrPresent(message = "Date must be valid")
    @NotNull(message = "Date field should not be empty")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @Getter
    @Setter
    @FutureOrPresent(message = "Date must be valid")
    @NotNull(message = "Date field should not be empty")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @Getter
    @Setter
    @NotEmpty(message = "At least one item should be included")
    private List<Item> items;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Client client;

    public Loan() {
        this.client = null;
        this.items = new ArrayList<>();
        this.startDate = null;
        this.endDate = null;
    }

    public Loan(LocalDate startDate,
                LocalDate endDate,
                List<Item> items,
                Client client) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.items = items;
        this.client = client;
    }
}
