package org.gassion.LibrarySpringApp.controller;


import org.gassion.LibrarySpringApp.model.Person;
import org.gassion.LibrarySpringApp.service.PersonService;
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
    private final PersonService personService;

    private final PersonValidate personValidate;

    @Autowired
    public PersonController(PersonService personService, PersonValidate personValidate) {
        this.personService = personService;
        this.personValidate = personValidate;
    }

    @GetMapping()
    public String getAllPerson(Model model){
        model.addAttribute("persons", personService.findAll());
        return "person/all_person";
    }

    @GetMapping("/new")
    public String addNewPersonView(Model model) {
        model.addAttribute("person", new Person());
        return "person/new";
    }

    @PostMapping()
    public String addNewPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidate.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "person/new";
        }

        personService.save(person);
        return "redirect:/person";
    }

    @GetMapping("/{id}")
    public String getPersonFromID(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.findOne(id));
        return "person/person";
    }

    @GetMapping("/{id}/edit")
    public String updatePersonView(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.findOne(id));
        return "person/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") Person person, @PathVariable int id) {
        personService.update(id, person);
        return "redirect:/person";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/person";
    }
}
