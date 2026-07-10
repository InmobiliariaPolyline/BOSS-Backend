package com.boos.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvidenciaFotoDTO {
    private Integer idFoto;
    private Integer idParteDiario;
    private String urlCloud;
    private String descripcion;
}
