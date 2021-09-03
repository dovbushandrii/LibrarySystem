package webapp.model.entities;

import lombok.Getter;
import lombok.Setter;
import webapp.model.enums.ItemType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Item {
    @Id
    @GeneratedValue
    @Getter
    private long id;

    @Getter
    @Setter
    private ItemType type;

    @Getter
    @Setter
    @NotEmpty(message = "Name should not be empty")
    @Size(max = 30, message = "Name must be up to 30 characters")
    private String name;

    public Item() {
        this.type = ItemType.NOT_DEF;
        this.name = "";
    }

    public Item(ItemType type, String name) {
        this.type = type;
        this.name = name;
    }
}
