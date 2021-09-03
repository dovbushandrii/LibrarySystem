package webapp.model.enums;

public enum ItemType {

    BOOK("Book"),
    CD("CD"),
    MAGAZINE("Magazine"),
    NOT_DEF("Not defined");

    private final String value;

    ItemType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
