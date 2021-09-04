package webapp.model.validators;

import org.springframework.expression.spel.standard.SpelExpressionParser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class ValidStartAndEndDatesValidator implements ConstraintValidator<ValidStartAndEndDates, Object> {

    private String startDate;
    private String endDate;

    @Override
    public void initialize(ValidStartAndEndDates constraintAnnotation) {
        startDate = constraintAnnotation.startDate();
        endDate = constraintAnnotation.endDate();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        SpelExpressionParser parser = new SpelExpressionParser();
        LocalDate startDateObj = (LocalDate) parser.parseExpression(startDate).getValue(value);
        LocalDate endDateObj = (LocalDate) parser.parseExpression(endDate).getValue(value);
        if(startDateObj != null && endDateObj != null) {
            boolean isValid = !endDateObj.isBefore(startDateObj);
            if(!isValid){
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                        .addPropertyNode( endDate ).addConstraintViolation();
            }
            return isValid;
        }
        return true;
    }
}
