package webapp.model.enums;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum ItemType {
    NOT_DEF,
    BOOK,
    CD,
    MAGAZINE;

    public static ItemType fromCode(int code) {
        return Arrays.stream(values())
                .filter(item -> code == item.ordinal())
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
