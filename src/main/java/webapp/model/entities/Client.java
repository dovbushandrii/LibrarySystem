package webapp.model.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Client implements Comparable {
    @Id
    @GeneratedValue
    @Getter
    private long id;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @Getter
    List<Loan> loans;

    @Getter
    @Setter
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String firstName;

    @Getter
    @Setter
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String lastName;

    @Getter
    @Setter
    @Past(message = "Date of birth must be valid")
    @NotNull(message = "Date field should not be empty")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

    @Getter
    @Setter
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    @Override
    public int compareTo(Object o) {
        if (o.getClass().getName().equals(this.getClass().getName())) {
            Client client = (Client) o;
            String thisName = firstName + " " + lastName;
            String compareName = client.firstName + " " + client.lastName;
            return thisName.compareTo(compareName);
        }
        throw new IllegalArgumentException("Incompatible class object was given");
    }
}
