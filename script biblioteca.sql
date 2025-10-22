-- Script: biblioteca_schema.sql (corregido de OCR)
DROP DATABASE IF EXISTS biblioteca;
CREATE DATABASE biblioteca
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
USE biblioteca;

-- Tabla categorias
DROP TABLE IF EXISTS categoria;

CREATE TABLE categoria (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  descripcion VARCHAR(255) DEFAULT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY ux_categoria_nombre (nombre)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla libros
DROP TABLE IF EXISTS libro;

CREATE TABLE libro (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(255) NOT NULL,
  autor VARCHAR(200) NOT NULL,
  isbn VARCHAR(20) DEFAULT NULL,
  descripcion TEXT DEFAULT NULL,
  categoria_id BIGINT UNSIGNED NOT NULL,
  fecha_publicacion DATE DEFAULT NULL,
  disponible BOOLEAN NOT NULL DEFAULT TRUE,
  precio DECIMAL(10,2) DEFAULT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY idx_libro_categoria (categoria_id),
  CONSTRAINT fk_libro_categoria FOREIGN KEY (categoria_id)
    REFERENCES categoria (id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla quejas
DROP TABLE IF EXISTS queja;

CREATE TABLE queja (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  nombre_cliente VARCHAR(150) DEFAULT NULL,
  email VARCHAR(200) DEFAULT NULL,
  telefono VARCHAR(30) DEFAULT NULL,
  tipo ENUM('QUEJA','SUGERENCIA','CONSULTA') NOT NULL DEFAULT 'CONSULTA',
  asunto VARCHAR(200) DEFAULT NULL,
  mensaje TEXT NOT NULL,
  tratado BOOLEAN NOT NULL DEFAULT FALSE,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY idx_queja_tipo (tipo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Datos de ejemplo: categorías
INSERT INTO categoria (nombre, descripcion) VALUES
('Ficción', 'Novelas y relatos de ficción.'),
('Ciencia', 'Libros de divulgación y textos científicos.');

-- Datos de ejemplo: libros
INSERT INTO libro (titulo, autor, isbn, descripcion, categoria_id, fecha_publicacion, precio)
VALUES
('Cien años de soledad', 'Gabriel García Márquez', '978-0307474728', 'Novela clásica de realismo mágico.', 1, '1967-06-05', 19.90),
('Breves respuestas a las grandes preguntas', 'Stephen Hawking', '978-0241346714', 'Divulgación científica sobre preguntas actuales.', 2, '2018-10-16', 14.50);

-- Datos de ejemplo: queja/sugerencia
INSERT INTO queja (nombre_cliente, email, telefono, tipo, asunto, mensaje) VALUES
('María Pérez', 'maria.perez@email.com', '506-8888-7777', 'SUGERENCIA', 'Ampliar horario', 'Sería ideal que la biblioteca abra más horas los fines de semana.');

