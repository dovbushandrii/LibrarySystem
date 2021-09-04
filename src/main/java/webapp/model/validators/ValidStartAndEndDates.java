/**
 * @file ValidStartAndEndDates.java
 * @brief This file contains interface of ValidStartAndEndDates annotation
 *
 * @author Andrii Dovbush
 */

package webapp.model.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Checks if the endDate field value is not before
 * startDate field value.
 * If endDate is before, adds Constraint Violation
 * to endDate field.
 */
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
