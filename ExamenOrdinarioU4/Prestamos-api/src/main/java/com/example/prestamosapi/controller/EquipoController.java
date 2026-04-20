package com.example.prestamosapi.controller;

import com.example.prestamosapi.model.Equipo;
import com.example.prestamosapi.model.Usuario;
import com.example.prestamosapi.repository.EquipoRepository;
import com.example.prestamosapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    private EquipoRepository repository;

    @PostMapping
    public Equipo create(@RequestBody Equipo equipo){
        return repository.save(equipo);
    }

    @GetMapping
    public List<Equipo> getAll(){
        return repository.findAll();
    }
}
