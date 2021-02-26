package ua.polkovnik.SpringBootApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.polkovnik.SpringBootApp.models.Book;
import ua.polkovnik.SpringBootApp.models.MockDB;

import java.util.HashMap;
import java.util.Map;


@Controller
public class BookController {

    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("books", MockDB.getBooks());
        return "main";
    }

    @GetMapping("/addBook")
    public String addBook(Model model){
        return "book";
    }

    @GetMapping("/error")
    public String error(Model model){
        return"error";
    }

    @PostMapping("/addBook")
    public ModelAndView addBook(@RequestParam String bookName,
                                @RequestParam String bookISBN,
                                @RequestParam String bookAuthor,
                                @RequestParam String bookDesc,
                                @ModelAttribute("flashAttribute") Object flashAttribute,
                                Model model){
        Book book = new Book(bookName,bookISBN,bookAuthor,bookDesc);
        boolean hasBook = MockDB.insert(book);
        if(hasBook){
              return new ModelAndView("redirect:/");
            }
        return new ModelAndView("redirect:/error");
    }

}
