package com.fide.biblioteca.repository;

import com.fide.biblioteca.domain.Libro;
import com.fide.biblioteca.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByCategoria(Categoria categoria);
    List<Libro> findByTituloContainingIgnoreCaseOrAutorContainingIgnoreCase(String titulo, String autor);
}
