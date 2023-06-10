package org.gassion.LibrarySpringApp.controller;


import org.gassion.LibrarySpringApp.dao.PersonDAO;
import org.gassion.LibrarySpringApp.model.Person;
import org.gassion.LibrarySpringApp.util.validation.PersonValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("person")
public class PersonController {
    private final PersonDAO personDAO;

    private final PersonValidate personValidate;

    @Autowired
    public PersonController(PersonDAO personDAO, PersonValidate personValidate) {
        this.personDAO = personDAO;
        this.personValidate = personValidate;
    }

    @GetMapping()
    public String getAllPerson(Model model){
        List<Person> personList =  personDAO.getAll();
        model.addAttribute("persons", personList);
        return "person/all_person";
    }

    @GetMapping("/new")
    public String addNewPersonView(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "person/new";
    }

    @PostMapping()
    public String addNewPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

        personValidate.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "person/new";
        }

        personDAO.add(person);
        return "redirect:/person";
    }

    @GetMapping("/{id}")
    public String getPersonFromID(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getFromID(id));
        model.addAttribute("books", personDAO.getAllBorrowedBook(id));
        return "person/person";
    }

    @GetMapping("/{id}/edit")
    public String updatePersonView(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getFromID(id));
        return "person/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") Person person, @PathVariable int id) {
        personDAO.update(id, person);
        return "redirect:/person";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/person";
    }
}
