package com.fide.biblioteca.service.impl;

import com.fide.biblioteca.domain.Queja;
import com.fide.biblioteca.repository.QuejaRepository;
import com.fide.biblioteca.service.QuejaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuejaServiceImpl implements QuejaService {

    private final QuejaRepository repo;

    public QuejaServiceImpl(QuejaRepository repo) {
        this.repo = repo;
    }

    @Override
    public Queja guardar(Queja q) {
        return repo.save(q);
    }

    @Override
    public List<Queja> listar() {
        return repo.findAll();
    }
}
