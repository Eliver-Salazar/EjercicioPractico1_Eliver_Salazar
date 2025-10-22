package com.fide.biblioteca.service.impl;

import com.fide.biblioteca.domain.Categoria;
import com.fide.biblioteca.repository.CategoriaRepository;
import com.fide.biblioteca.service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository repo;

    public CategoriaServiceImpl(CategoriaRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Categoria> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return repo.findById(id);
    }

    @Override
    public Categoria guardar(Categoria c) {
        return repo.save(c);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
