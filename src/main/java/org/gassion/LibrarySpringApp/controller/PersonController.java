package org.gassion.LibrarySpringApp.controller;

import org.gassion.LibrarySpringApp.dao.PersonDAO;
import org.gassion.LibrarySpringApp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
        List<Person> personList =  personDAO.getAll();
        model.addAttribute("persons", personList);
        return "person/all_person";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "person/new";
    }

    @PostMapping()
    public String addPerson(@ModelAttribute("person") Person person) {
        personDAO.add(person);
        return "redirect:/person";
    }
}
