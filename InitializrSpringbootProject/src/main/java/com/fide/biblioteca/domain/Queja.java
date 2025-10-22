package com.fide.biblioteca.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "queja")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Queja {

    public enum Tipo { QUEJA, SUGERENCIA, CONSULTA }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_cliente", length = 150)
    private String nombreCliente;

    @Email
    @Column(length = 200)
    private String email;

    @Column(length = 30)
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 15)
    private Tipo tipo = Tipo.CONSULTA;

    @Column(length = 200)
    private String asunto;

    @NotBlank
    @Lob
    private String mensaje;

    @Column(nullable = false)
    private boolean tratado = false;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
