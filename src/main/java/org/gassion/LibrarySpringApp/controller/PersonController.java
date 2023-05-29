package org.gassion.LibrarySpringApp.controller;

import org.gassion.LibrarySpringApp.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("person")
public class PersonController {
    private final PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String allPerson(Model model){
        model.addAttribute("persons", personDAO.getAll());
        return "person/all_person";
    }
}
