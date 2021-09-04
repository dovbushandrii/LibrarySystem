package webapp.model.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = ValidStartAndEndDatesValidator.class)
public @interface ValidStartAndEndDates {
    String startDate();
    String endDate();

    String message() default "Only field names are valid options";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
