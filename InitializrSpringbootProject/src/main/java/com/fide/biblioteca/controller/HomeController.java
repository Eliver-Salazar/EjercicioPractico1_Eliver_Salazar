package com.fide.biblioteca.controller;

import com.fide.biblioteca.service.LibroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final LibroService libroService;

    public HomeController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping({"/", "/inicio"})
    public String inicio(Model model) {
        model.addAttribute("libros", libroService.listar());
        return "index";
    }
}
