package com.boos.backend.dto;

import com.boos.backend.util.ProveedorNube;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObraArchivoDTO {
    private Integer idObraArchivo;

    @NotNull(message = "El ID de la obra es obligatorio")
    private Integer idObra;

    @NotBlank(message = "El nombre del archivo es obligatorio")
    private String nombreArchivo;

    @NotBlank(message = "El tipo de archivo es obligatorio")
    private String tipoArchivo;

    @NotBlank(message = "El ID de archivo en la nube es obligatorio")
    private String fileIdNube;

    @NotNull(message = "El proveedor de nube es obligatorio")
    private ProveedorNube proveedorNube;

    private String urlAcceso;
    private String categoria;
    private String tamano;
    private String version;
    private String estadoSincronizacion;
}
