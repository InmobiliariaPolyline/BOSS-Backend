package com.boos.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MovimientoMaterialDTO {
    private Integer idMovimiento;
    private Integer idParteDiario;
    private Integer idMaterial;
    private Double cantidad;
    private String tipoMovimiento;

}
