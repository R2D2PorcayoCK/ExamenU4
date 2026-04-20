package com.example.prestamosapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
@Getter
@Setter
@NoArgsConstructor
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private LocalDate fechaSolicitud;
    private LocalDate fechaDevolucion;
    private String estado; //(SOLICITADO, APROBADO, RECHAZADO, DEVUELTO)

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;

}
