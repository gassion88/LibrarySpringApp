package org.gassion.LibrarySpringApp.util.validation;

import org.gassion.LibrarySpringApp.model.Person;
import org.gassion.LibrarySpringApp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidate implements Validator {
    private final PersonService personService;

    @Autowired
    public PersonValidate(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personService.findByPhoneNumber(person.getPhoneNumber()) != null) {
            errors.rejectValue("phoneNumber", "", "This phone number is already in use");
        }
    }
}
