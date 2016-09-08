package net.openandshut.web.validators;

import net.openandshut.entities.Candidate;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CandidateValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return Candidate.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
    ValidationUtils.rejectIfEmpty(errors, "date", "date.empty");
  }
}
