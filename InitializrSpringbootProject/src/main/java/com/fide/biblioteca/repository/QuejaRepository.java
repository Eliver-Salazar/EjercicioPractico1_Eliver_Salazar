package com.fide.biblioteca.repository;

import com.fide.biblioteca.domain.Queja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuejaRepository extends JpaRepository<Queja, Long> {
}
