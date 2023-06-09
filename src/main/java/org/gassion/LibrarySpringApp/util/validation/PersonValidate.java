package org.gassion.LibrarySpringApp.util.validation;

import org.gassion.LibrarySpringApp.dao.PersonDAO;
import org.gassion.LibrarySpringApp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidate implements Validator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidate(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personDAO.getFromNumber(person.getPhoneNumber()).isPresent()) {
            errors.rejectValue("phoneNumber", "", "This phone number is already in use");
        }
    }
}
