package webapp.model.entities;

import lombok.Getter;
import lombok.Setter;
import webapp.model.entities.embeddable.Name;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Client {
    @Id
    @GeneratedValue
    private long id;

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
