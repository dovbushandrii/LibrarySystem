package webapp.model.enums;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum LoanStatus {
    NOT_DEF,
    NEW,
    ONGOING,
    EXPIRED,
    CLOSED;

    public static LoanStatus fromCode(int code) {
        return Arrays.stream(values())
                .filter(item -> code == item.ordinal())
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
