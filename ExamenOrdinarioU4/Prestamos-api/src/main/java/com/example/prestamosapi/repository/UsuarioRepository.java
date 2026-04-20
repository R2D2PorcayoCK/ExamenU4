package com.example.prestamosapi.repository;

import com.example.prestamosapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    Usuario findByNombre(String nombre);
}
