package com.kabz.springboot.controller;

import com.kabz.springboot.models.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    @Value("${welcome.message}")
    private String welcomeMsg;
    private static final List<Book> _books = new ArrayList<>();

    static {
        _books.add(new Book("Java Programming", "Tenzin"));
        _books.add(new Book("C# development", "Scott"));
    }

    //@RequestMapping(name = "/", method = RequestMethod.GET)
    @GetMapping("/")
    public List<Book> getBooks() {
        System.out.println("getting books...");
        return _books;
    }

    @GetMapping("/msg")
    public String welcome() {
        return welcomeMsg;
    }
}

