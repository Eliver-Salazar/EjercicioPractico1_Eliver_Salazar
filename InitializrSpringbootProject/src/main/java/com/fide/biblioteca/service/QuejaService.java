package com.fide.biblioteca.service;

import com.fide.biblioteca.domain.Queja;
import java.util.List;

public interface QuejaService {
    Queja guardar(Queja q);
    List<Queja> listar();
}
