package com.boos.backend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GastoCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idGastoCaja;

    @ManyToOne
    @JoinColumn(name = "id_caja_chica", nullable = false, foreignKey = @ForeignKey(name = "FK_GASTO_CAJA"))
    private CajaChica cajaChica;

    @Column(nullable = false, length = 50)
    private String categoriaGasto;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false)
    private LocalDateTime fechaGasto;

    @Column(nullable = false, length = 200)
    private String concepto;
}