package com.boos.backend.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MaterialCreateDTO {

    private Integer idMaterial;

    @NotNull(message = "El ID de la obra es obligatorio")
    private Integer idObra;

    @NotBlank(message = "El nombre del material es obligatorio")
    @Size(max = 100, message = "El nombre del material no debe exceder los 100 caracteres")
    private String nombreMaterial;

    @NotBlank(message = "La categoría es obligatoria")
    @Size(max = 50, message = "La categoría no debe exceder los 50 caracteres")
    private String categoria;

    @NotBlank(message = "La unidad de medida es obligatoria")
    @Size(max = 20, message = "La unidad de medida no debe exceder los 20 caracteres")
    private String unidadMedida;

    @NotNull(message = "El precio unitario es obligatorio")
    @Min(value = 0, message = "El precio unitario debe ser mayor o igual a 0")
    private Double precioUnitario;

    @NotNull(message = "El stock actual es obligatorio")
    @Min(value = 0, message = "El stock actual no puede ser negativo")
    private Integer stockActual;

    @NotNull(message = "La fecha de compra es obligatoria")
    private LocalDate fechaCompra;
}
