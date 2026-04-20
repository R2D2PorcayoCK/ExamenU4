package com.example.prestamosapi.controller;

import com.example.prestamosapi.model.Usuario;
import com.example.prestamosapi.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    public Usuario create(@RequestBody Usuario usuario){
        return repository.save(usuario);
    }

    @GetMapping
    public List<Usuario> getAll(){
        return repository.findAll();
    }
}

