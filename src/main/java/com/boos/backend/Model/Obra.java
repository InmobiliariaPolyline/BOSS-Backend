package com.boos.backend.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Obra {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idObra;

    @ManyToOne // FK
    @JoinColumn(name = "id_cliente", nullable = false, foreignKey= @ForeignKey(name="FK_OBRA_CLIENTE"))
    private Cliente cliente;

    @JsonManagedReference
    @ManyToMany //FK
    @JoinTable(
            name = "obra_empleado",
            joinColumns = @JoinColumn(name = "id_obra"),
            inverseJoinColumns = @JoinColumn(name = "id_empleado")
    )
    private List<Empleado> empleados;



    @Column(nullable = false, length = 150)
    private String nombreObra;

    @Column(nullable = false, length = 100)
    private String ubicacion;

    @Column(nullable = false)
    private Double presupuestoTotal;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = true)
    private LocalDate fechaFinEstimada;

    @Column(nullable = false)
    private boolean estado;
}
