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
public class CajaChica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idCajaChica;

    @ManyToOne
    @JoinColumn(name = "id_obra", nullable = false, foreignKey = @ForeignKey(name = "FK_CAJA_OBRA"))
    private Obra obra;

    @Column(nullable = false)
    private Double saldoActual;

}
