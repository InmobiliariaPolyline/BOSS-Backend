package com.boos.backend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Donacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idDonacion;

    @ManyToOne
    @JoinColumn(name = "id_obra", nullable = false, foreignKey = @ForeignKey(name = "FK_DONACION_OBRA"))
    private Obra obra;

    @Column(nullable = false, length = 100)
    private String nombreDonante;

    @Column(nullable = false, length = 50)
    private String tipoDonacion;

    @Column(nullable = true, length = 150)
    private String descripcion;

    @Column(nullable = true)
    private Integer cantidadDonada;

    @Column(nullable = true)
    private Double precioUnitario;

    @Column(nullable = false)
    private Double montoTotal;

    @Column(nullable = false)
    private LocalDate fechaRegistro;
}