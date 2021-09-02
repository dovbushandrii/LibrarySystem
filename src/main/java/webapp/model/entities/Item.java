package webapp.model.entities;

import lombok.Getter;
import webapp.model.enums.ItemType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy="item")
    @Getter
    List<Loan> loans;

    @Getter
    private final ItemType type;

    @Getter
    private final String name;

    public Item() {
        this.type = ItemType.NOT_DEF;
        this.name = "";
    }

    public Item(ItemType type, String name) {
        this.type = type;
        this.name = name;
    }
}
