package webapp.model.entities;

import lombok.Getter;
import lombok.Setter;
import webapp.model.entities.embeddable.Name;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy="client")
    @Getter
    List<Loan> loans;

    @Getter
    @Setter
    private Name name;

    @Getter
    @Setter
    private LocalDate dateOfBirth;

    @Getter
    @Setter
    private String email;
}
