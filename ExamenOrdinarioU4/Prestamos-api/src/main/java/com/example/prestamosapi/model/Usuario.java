package com.example.prestamosapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String email;

    private String tipoUsuario; //(NORMAL, ADMIN)
    private boolean activo;

    @OneToMany(mappedBy = "client")
    private List<Prestamo> prestamos;

}


/* ANOTACIONES EXAMEN
Pasos para hacer mi examen :V

1. model/identity/dto -listo
2. repository -listo
3. service
4. controller -listo

 */
