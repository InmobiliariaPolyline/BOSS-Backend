package com.boos.backend.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ParteDiario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idParteDiario;

    @ManyToOne
    @JoinColumn(name = "id_obra", nullable = false, foreignKey = @ForeignKey(name = "FK_PARTE_OBRA"))
    private Obra obra;

    @Column(nullable = false)
    private LocalDateTime fechaInforme;

    @Column(nullable = false, length = 150)
    private String elaboradoPor;

    @Column(nullable = false, length = 150)
    private String nombreParte;

    @Column(nullable = false)
    private Double jornadaLaboral;

    @Column(nullable = false)
    private Double cantidadEjecutada;

    @Column(nullable = false, length = 10)
    private String unidadMedida;

    @Column(nullable = false)
    private Double rendimientoReal;

    @Column(nullable = false)
    private Double rendimientoEsperado;

    @Column(length = 1000)
    private String observaciones;

    @JsonManagedReference
    @OneToMany(mappedBy = "parteDiario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CostoDiario> costos;

    @JsonManagedReference
    @OneToMany(mappedBy = "parteDiario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EvidenciaFoto> fotos;

    @JsonManagedReference
    @OneToMany(mappedBy = "parteDiario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovimientoMaterial> movimientos;

}