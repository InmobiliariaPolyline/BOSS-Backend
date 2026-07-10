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
public class EvidenciaFoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idFoto;

    @ManyToOne
    @JoinColumn(name = "id_parte", nullable = false, foreignKey = @ForeignKey(name = "FK_EVIDENCIA_PARTE"))
    @JsonBackReference
    private ParteDiario parteDiario;

    @Column(nullable = false, length = 255)
    private String urlCloud;

    @Column(length = 200)
    private String descripcion;
}