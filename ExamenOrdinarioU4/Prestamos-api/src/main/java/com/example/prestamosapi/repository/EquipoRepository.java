package com.example.prestamosapi.repository;

import com.example.prestamosapi.model.Equipo;
import com.example.prestamosapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipoRepository extends JpaRepository<Equipo,Integer> {
    Equipo findByNombre(String nombre);
}
