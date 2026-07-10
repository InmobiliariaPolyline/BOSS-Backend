package com.boos.backend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idCliente;

    @Column(nullable = false, length = 100)
    private String nombreCompleto;

    @Column(nullable = false, unique = true)
    private String rucDNI;

    @Column(nullable = false, length = 150)
    private String razonSocial;

    @Column(nullable = false, length = 200)
    private String direccion;

    @Column(nullable = false, length = 200)
    private String nombreContacto;

    @Column(nullable = false)
    private Integer telefono;

    @Column(nullable = false, length = 100)
    private String email;
}