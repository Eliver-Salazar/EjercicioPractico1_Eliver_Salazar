package com.fide.biblioteca.controller;

import com.fide.biblioteca.domain.Categoria;
import com.fide.biblioteca.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", service.listar());
        model.addAttribute("categoria", new Categoria());
        return "categorias/lista";
    }

    @PostMapping
    public String guardar(@Valid @ModelAttribute("categoria") Categoria c, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("categorias", service.listar());
            return "categorias/lista";
        }
        service.guardar(c);
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        var cat = service.buscarPorId(id).orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
        model.addAttribute("categoria", cat);
        model.addAttribute("categorias", service.listar());
        return "categorias/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/categorias";
    }
}
