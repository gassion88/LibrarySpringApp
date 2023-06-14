package org.gassion.LibrarySpringApp.controller;


import org.gassion.LibrarySpringApp.model.Book;
import org.gassion.LibrarySpringApp.model.Person;
import org.gassion.LibrarySpringApp.service.BookService;
import org.gassion.LibrarySpringApp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("book")
public class BookController {
    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
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

        bookService.save(newBook);
        return "redirect:/book";
    }

    @GetMapping("/{id}")
    public String viewBook(@PathVariable("id") int id, Model model) {
        Book book = bookService.findOne(id);
        model.addAttribute("book", book);

        if (book.getBorrowedPerson() == null){
            model.addAttribute("allPerson", personService.findAll());
            model.addAttribute("selectedPerson", new Person());
        }

        return "book/book";
    }

    @GetMapping("/{id}/edit")
    public String editBookView(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.findOne(id));
        return "book/edit";
    }


    @PatchMapping("/{id}")
    public String editBook(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        bookService.update(id, book);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}/free")
    public String freeBook(@PathVariable("id") int bookID) {
        Book book = bookService.findOne(bookID);
        book.setBorrowedPerson(null);
        bookService.save(book);

        return "redirect:/book/" + bookID;
    }

    @PostMapping("/{id}/borrow")
    public String borrowBook(@PathVariable("id") int bookID, @ModelAttribute("selectedPerson") Person person) {
        Book book = bookService.findOne(bookID);
        book.setBorrowedPerson(person);
        bookService.save(book);

        return "redirect:/book/" + bookID;
    }
}
