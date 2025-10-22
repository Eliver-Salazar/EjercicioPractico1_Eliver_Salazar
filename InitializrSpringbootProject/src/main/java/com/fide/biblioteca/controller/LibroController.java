package com.fide.biblioteca.controller;

import com.fide.biblioteca.domain.Libro;
import com.fide.biblioteca.service.CategoriaService;
import com.fide.biblioteca.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;
    private final CategoriaService categoriaService;

    public LibroController(LibroService libroService, CategoriaService categoriaService) {
        this.libroService = libroService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listar(@RequestParam(value = "q", required = false) String q, Model model) {
        model.addAttribute("libros", libroService.buscar(q));
        model.addAttribute("q", q == null ? "" : q);
        return "libros/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("libro", new Libro());
        model.addAttribute("categorias", categoriaService.listar());
        return "libros/form";
    }

    @PostMapping
    public String guardar(@Valid @ModelAttribute("libro") Libro libro, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listar());
            return "libros/form";
        }
        libroService.guardar(libro);
        return "redirect:/libros";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        var libro = libroService.buscarPorId(id).orElseThrow(() -> new IllegalArgumentException("Libro no encontrado"));
        model.addAttribute("libro", libro);
        model.addAttribute("categorias", categoriaService.listar());
        return "libros/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        libroService.eliminar(id);
        return "redirect:/libros";
    }
}
