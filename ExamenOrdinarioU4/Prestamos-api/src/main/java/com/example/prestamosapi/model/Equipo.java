package com.example.prestamosapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "equipos")
@Getter
@Setter
@NoArgsConstructor
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String tipo; //(LAPTOP, PROYECTOR, TABLET, etc.)
    private boolean disponible; // (boolean)

    @OneToMany(mappedBy = "client")
    private List<Equipo> equipos;
}
