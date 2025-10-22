package com.fide.biblioteca.service;

import com.fide.biblioteca.domain.Libro;
import java.util.List;
import java.util.Optional;

public interface LibroService {
    List<Libro> listar();
    Optional<Libro> buscarPorId(Long id);
    Libro guardar(Libro l);
    void eliminar(Long id);
    List<Libro> buscar(String q);
}
