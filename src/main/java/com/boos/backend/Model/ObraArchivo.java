package com.boos.backend.Model;

import com.boos.backend.util.ProveedorNube;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "obra_archivos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ObraArchivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idObraArchivo;

    @ManyToOne
    @JoinColumn(name = "id_obra", nullable = false, foreignKey = @ForeignKey(name = "FK_OBRA_ARCHIVO_OBRA"))
    @JsonBackReference
    private Obra obra;

    @Column(name = "nombre_archivo", nullable = false, length = 255)
    private String nombreArchivo;

    @Column(name = "tipo_archivo", nullable = false, length = 50)
    private String tipoArchivo;

    @Column(name = "file_id_nube", nullable = false, length = 255)
    private String fileIdNube;

    @Enumerated(EnumType.STRING)
    @Column(name = "proveedor_nube", nullable = false, length = 50)
    private ProveedorNube proveedorNube;

    @Column(name = "url_acceso", length = 500)
    private String urlAcceso;

    @Column(name = "categoria", length = 100)
    private String categoria;

    @Column(name = "tamano", length = 50)
    private String tamano;

    @Column(name = "version", length = 20)
    private String version;

    @Column(name = "estado_sincronizacion", length = 50)
    private String estadoSincronizacion;
}
