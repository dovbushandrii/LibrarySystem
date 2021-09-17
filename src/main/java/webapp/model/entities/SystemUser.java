package webapp.model.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity
public class SystemUser {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String username;

    private String password;
}
