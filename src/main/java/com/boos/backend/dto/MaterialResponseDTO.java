package com.boos.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialResponseDTO {
    private Integer idMaterial;
    private Integer idObra;
    private String nombreMaterial;
    private String categoria;
    private String unidadMedida;
    private Double precioUnitario;
    private Integer stockActual;
    private LocalDate fechaCompra;
}
