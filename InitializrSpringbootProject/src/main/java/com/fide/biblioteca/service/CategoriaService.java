package com.fide.biblioteca.service;

import com.fide.biblioteca.domain.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categoria> listar();
    Optional<Categoria> buscarPorId(Long id);
    Categoria guardar(Categoria c);
    void eliminar(Long id);
}
