package com.fide.biblioteca.service.impl;

import com.fide.biblioteca.domain.Libro;
import com.fide.biblioteca.repository.LibroRepository;
import com.fide.biblioteca.service.LibroService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository repo;

    public LibroServiceImpl(LibroRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Libro> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Libro> buscarPorId(Long id) {
        return repo.findById(id);
    }

    @Override
    public Libro guardar(Libro l) {
        return repo.save(l);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Libro> buscar(String q) {
        if (q == null || q.isBlank()) return repo.findAll();
        return repo.findByTituloContainingIgnoreCaseOrAutorContainingIgnoreCase(q, q);
    }
}
