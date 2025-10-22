package com.fide.biblioteca.controller;

import com.fide.biblioteca.domain.Queja;
import com.fide.biblioteca.service.QuejaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/quejas")
public class QuejaController {

    private final QuejaService service;

    public QuejaController(QuejaService service) {
        this.service = service;
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("queja", new Queja());
        return "quejas/form";
    }

    @PostMapping
    public String guardar(@Valid @ModelAttribute("queja") Queja q, BindingResult br) {
        if (br.hasErrors()) {
            return "quejas/form";
        }
        service.guardar(q);
        return "redirect:/";
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("quejas", service.listar());
        return "quejas/lista";
    }
}
