package com.boos.backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idEmpleado;

    @JsonIgnore
    @ManyToMany(mappedBy = "empleados",cascade = CascadeType.REMOVE)
    private List<Obra> obras;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(nullable = false, unique = true)
    private Integer dni;

    @Column(nullable = false, length = 50)
    private String cargo;

    @Column(nullable = false, length = 10)
    private Integer telefono;

    @Column(nullable = false, length = 50)
    private String correoElectronico;

    @Column(nullable = false, length = 200)
    private String direccion;

    @Column(nullable = false)
    private String observaciones;

    @Column(nullable = false)
    private boolean estado;

}