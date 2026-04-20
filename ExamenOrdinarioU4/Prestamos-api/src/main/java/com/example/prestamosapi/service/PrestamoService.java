package com.example.prestamosapi.service;

import com.example.prestamosapi.model.Equipo;
import com.example.prestamosapi.model.Prestamo;
import com.example.prestamosapi.model.Usuario;
import com.example.prestamosapi.repository.EquipoRepository;
import com.example.prestamosapi.repository.PrestamoRepository;
import com.example.prestamosapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrestamoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private PrestamoRepository prestamoRepository;

    public List<Prestamo> findAll(){
        return prestamoRepository.findAll();
    }

    public Prestamo createPrestamo(Integer usuarioId, Integer equipoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();
        Equipo equipo = equipoRepository.findById(equipoId).orElseThrow();

        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);
        prestamo.setEquipo(equipo);
        prestamo.setFechaSolicitud(LocalDate.now());

        List<Prestamo> activos = prestamoRepository.findAll()
                .stream()
                .filter(p -> p.getUsuario().getId().equals(usuarioId))
                .filter(p -> p.getEstado().equals("SOLICITADO") || p.getEstado().equals("APROBADO"))
                .toList();

        boolean usuarioActivo = usuario.isActivo();
        boolean equipoDisponible = equipo.isDisponible();
        boolean limite = activos.size() < 2;

        if (usuarioActivo && equipoDisponible && limite) {
            prestamo.setEstado("SOLICITADO");
        } else {
            prestamo.setEstado("RECHAZADO");
        }

        return prestamoRepository.save(prestamo);
    }

    public Prestamo aprobarPrestamo(Integer idPrestamo) {
        Prestamo prestamo = prestamoRepository.findById(idPrestamo).orElseThrow();

        if (!prestamo.getEstado().equals("SOLICITADO")) {
            throw new RuntimeException("Solo SOLICITADOS");
        }

        prestamo.setEstado("APROBADO");

        Equipo equipo = prestamo.getEquipo();
        equipo.setDisponible(false);
        equipoRepository.save(equipo);

        return prestamoRepository.save(prestamo);
    }

    public Prestamo registrarDevolucion(Integer idPrestamo) {
        Prestamo prestamo = prestamoRepository.findById(idPrestamo).orElseThrow();

        if (!prestamo.getEstado().equals("APROBADO")) {
            throw new RuntimeException("Solo APROBADOS");
        }

        prestamo.setEstado("DEVUELTO");
        prestamo.setFechaDevolucion(LocalDate.now());

        Equipo equipo = prestamo.getEquipo();
        equipo.setDisponible(true);
        equipoRepository.save(equipo);

        return prestamoRepository.save(prestamo);
    }

    public Prestamo rechazarPrestamo(Integer idPrestamo) {
        Prestamo prestamo = prestamoRepository.findById(idPrestamo).orElseThrow();

        if (!prestamo.getEstado().equals("SOLICITADO")) {
            throw new RuntimeException("Solo SOLICITADOS");
        }

        prestamo.setEstado("RECHAZADO");

        return prestamoRepository.save(prestamo);
    }

    public Usuario findUsuarioByNombre(String nombre){
        Usuario usuario = usuarioRepository.findByNombre(nombre);
        if(usuario == null){
            throw new RuntimeException("Usuario no encontrado");
        }
        return usuario;
    }

    public Equipo findEquipoByNombre(String nombre){
        if(nombre == null || nombre.trim().isEmpty()){
            throw new RuntimeException("Nombre inválido");
        }

        Equipo equipo = equipoRepository.findByNombre(nombre);
        if(equipo == null){
            throw new RuntimeException("Equipo no encontrado");
        }
        return equipo;
    }

    public List<Equipo> buscarEquipoDisponiblePorNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new RuntimeException("Nombre inválido");
        }

        List<Equipo> resultado = new ArrayList<>();

        for (Equipo e : equipoRepository.findAll()) {
            if (e.isDisponible() && e.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultado.add(e);
            }
        }

        return resultado;
    }

}