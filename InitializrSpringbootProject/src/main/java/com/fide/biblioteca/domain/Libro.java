package com.fide.biblioteca.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "libro")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Column(name = "titulo", nullable = false, length = 255)
    private String titulo;

    @NotBlank(message = "El autor es obligatorio")
    @Column(nullable = false, length = 200)
    private String autor;

    @Column(length = 20)
    private String isbn;

    @Lob
    private String descripcion;

    @NotNull(message = "La categoría es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_libro_categoria"))
    private Categoria categoria;

    private LocalDate fechaPublicacion;

    @NotNull
    @Column(nullable = false)
    private Boolean disponible = Boolean.TRUE;

    private BigDecimal precio;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
        if (disponible == null) disponible = Boolean.TRUE;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
