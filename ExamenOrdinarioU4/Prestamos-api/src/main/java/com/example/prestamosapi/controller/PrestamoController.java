package com.example.prestamosapi.controller;

import com.example.prestamosapi.model.Equipo;
import com.example.prestamosapi.model.Prestamo;
import com.example.prestamosapi.repository.EquipoRepository;
import com.example.prestamosapi.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {
    @Autowired
    private PrestamoRepository repository;

    @PostMapping
    public Prestamo create(@RequestBody Prestamo prestamo){
        return repository.save(prestamo);
    }

    @GetMapping
    public List<Prestamo> getAll(){
        return repository.findAll();
    }
}


/*
⚙️ SERVICIOS 1. Servicio principalsolicitarPrestamo(PrestamoDTO dto) → ⭐ 40 pts
Validaciones:
Validar que el usuario esté activo
Validar que el equipo esté disponible
Validar que el usuario no tenga más de 2 préstamos activos
Si falla algo → estado = RECHAZADO
Si todo pasa → estado = SOLICITADO
⚙️ 2. Servicio secundarioaprobarPrestamo(Long idPrestamo) → ⭐ 20 pts
Solo se pueden aprobar préstamos en estado SOLICITADO
Cambia estado a APROBADO
Marca equipo como NO disponible
⚙️ 3. Servicio secundarioregistrarDevolucion(Long idPrestamo) → ⭐ 20 pts
Cambia estado a DEVUELTO
Marca equipo como disponible
⚙️ 4. Servicio secundariorechazarPrestamo(Long idPrestamo) → ⭐ 10 pts
Cambia estado a RECHAZADO
Solo si está en SOLICITADO
⚙️ 5. Servicio simplebuscarEquipoDisponiblePorNombre(String nombre) → ⭐ 10 pts
Debe filtrar solo equipos disponibles

        Servicio

Puntos

        solicitarPrestamo

40

aprobarPrestamo

20

registrarDevolucion

20

rechazarPrestamo

10

búsqueda equipo

10

TOTAL

100
*/