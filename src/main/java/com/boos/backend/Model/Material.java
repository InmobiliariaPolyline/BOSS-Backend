package com.boos.backend.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idMaterial;

    @ManyToOne
    @JoinColumn(name = "id_obra", nullable = false, foreignKey = @ForeignKey(name = "FK_MATERIAL_OBRA"))
    private Obra obra;

    @Column(nullable = false, length = 100)
    private String nombreMaterial;

    @Column(nullable = false, length = 50)
    private String categoria;

    @Column(nullable = false, length = 20)
    private String unidadMedida;

    @Column(nullable = false)
    private Double precioUnitario;

    @Column(nullable = false)
    private Integer stockActual;

    @Column(nullable = false)
    private LocalDate fechaCompra;

    @Column(nullable = false)
    private boolean estado = true;

    @OneToMany(mappedBy = "material", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<MovimientoMaterial> movimientos = new ArrayList<>();
}