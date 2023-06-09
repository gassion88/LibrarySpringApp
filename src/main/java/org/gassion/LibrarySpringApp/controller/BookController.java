package org.gassion.LibrarySpringApp.controller;

import jakarta.validation.Valid;
import org.gassion.LibrarySpringApp.dao.BookDAO;
import org.gassion.LibrarySpringApp.dao.PersonDAO;
import org.gassion.LibrarySpringApp.model.Book;
import org.gassion.LibrarySpringApp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("book")
public class BookController {
    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    @Autowired
    public BookController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookDAO.getAll());
        return "book/all";
    }

    @GetMapping("/new")
    public String addNewBookView(Model model) {
        model.addAttribute("newBook", new Book());
        return "book/new";
    }

    @PostMapping
    public String addNewBook(@ModelAttribute("newBook") @Valid Book newBook, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "book/new";
        }

        bookDAO.add(newBook);
        return "redirect:/book";
    }

    @GetMapping("/{id}")
    public String viewBook(@PathVariable("id") int id, Model model) {
        Person person = bookDAO.getPersonBorrowed(id);
        model.addAttribute("book", bookDAO.getFromID(id));
        model.addAttribute("person", person);

        if (person == null){
            model.addAttribute("allPerson", personDAO.getAll());
            model.addAttribute("selectedPerson", new Person());
        }

        return "book/book";
    }

    @GetMapping("/{id}/edit")
    public String editBookView(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getFromID(id));
        return "book/edit";
    }


    @PatchMapping("/{id}")
    public String editBook(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        bookDAO.update(id, book);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}/free")
    public String freeBook(@PathVariable("id") int bookID) {
        bookDAO.deleteBookInBorrowed(bookID);
        return "redirect:/book/" + bookID;
    }

    @PostMapping("/{id}/borrow")
    public String borrowBook(@PathVariable("id") int bookID, @ModelAttribute("selectedPerson") Person person) {
        bookDAO.addBookToBorrowed(person.getId(), bookID);
        return "redirect:/book/" + bookID;
    }
}
