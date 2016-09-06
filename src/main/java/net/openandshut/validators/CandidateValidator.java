package net.openandshut.validators;

import net.openandshut.entities.Candidate;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CandidateValidator implements Validator {
  @Override
  public boolean supports(Class<?> clazz) {
    return Candidate.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
    Candidate p = (Candidate) target;
    if (StringUtils.isEmpty(p.getName())) {
      errors.rejectValue("name", "empty.string");
    } else if (p.getDate() == null) {
      errors.rejectValue("date", "null.date");
    }
  }
}
