package org.gassion.LibrarySpringApp.controller;

import org.gassion.LibrarySpringApp.dao.BookDAO;
import org.gassion.LibrarySpringApp.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("book")
public class BookController {
    private final BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {
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
    public String addNewBook(@ModelAttribute("newBook") Book newBook) {
        bookDAO.add(newBook);
        return "redirect:/book";
    }

    @GetMapping("/{id}")
    public String viewBook(@PathVariable("id") int id, Model model) {
        Book book = bookDAO.getFromID(id);
        model.addAttribute("book", book);
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
}
