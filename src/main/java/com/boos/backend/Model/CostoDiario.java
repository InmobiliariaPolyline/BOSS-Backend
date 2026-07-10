package com.boos.backend.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class CostoDiario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idCosto;

    @ManyToOne
    @JoinColumn(name = "id_parte_diario", nullable = false)
    @JsonBackReference
    private ParteDiario parteDiario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoCosto tipo;

    @Column(nullable = false, length = 200)
    private String descripcion;

    @Column(nullable = false)
    private Double cantidad;

    @Column(nullable = false)
    private Double costoUnitario;

    @Column(nullable = false)
    private Double costoTotal;
}
