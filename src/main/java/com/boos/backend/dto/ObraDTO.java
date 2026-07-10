package com.boos.backend.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObraDTO {
    private Integer idObra;

    @NotNull(message = "El ID del cliente es obligatorio")
    private Integer idCliente;


    @NotBlank(message = "El nombre de la obra es obligatorio")
    @Size(min = 2, max = 150, message = "El nombre de la obra debe tener entre 2 y 150 caracteres")
    private String nombreObra;

    @NotBlank(message = "La ubicación es obligatoria")
    @Size(max = 255, message = "La ubicación no debe exceder los 255 caracteres")
    private String ubicacion;

    @NotNull(message = "El presupuesto total es obligatorio")
    @Positive(message = "El presupuesto total debe ser mayor a 0")
    @Digits(integer = 12, fraction = 2, message = "El presupuesto total debe tener máximo 2 decimales")
    private Double presupuestoTotal;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha fin estimada es obligatoria")
    private LocalDate fechaFinEstimada;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;

    @Valid
    private List<EmpleadoResponseDTO> empleados;
}
