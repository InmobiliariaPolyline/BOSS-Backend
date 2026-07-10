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
public class MovimientoMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idMovimiento;

    @ManyToOne
    @JoinColumn(name = "id_parte_diario", nullable = true, foreignKey = @ForeignKey(name = "FK_MOVIMIENTO_PARTE"))
    private ParteDiario parteDiario;

    @ManyToOne
    @JoinColumn(name = "id_material", nullable = true, foreignKey = @ForeignKey(name = "FK_MOVIMIENTO_MATERIAL"))
    private Material material;

    @Column(nullable = false)
    private Double cantidad;

    @Column(nullable = false, length = 20)
    private String tipoMovimiento; //
}