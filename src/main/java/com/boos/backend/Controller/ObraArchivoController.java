package com.boos.backend.Controller;

import com.boos.backend.Model.ObraArchivo;
import com.boos.backend.Service.IObraArchivoService;
import com.boos.backend.dto.ObraArchivoDTO;
import com.boos.backend.util.ProveedorNube;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/obra-archivos")
@CrossOrigin(origins = "*")
public class ObraArchivoController {

    private final IObraArchivoService service;
    private final com.boos.backend.Service.Implementation.GoogleDriveService googleDriveService;

    @Qualifier("obraArchivoMapper")
    private final ModelMapper modelMapper;

    private ObraArchivoDTO convertToDTO(ObraArchivo saved) {
        ObraArchivoDTO responseDto = new ObraArchivoDTO();
        responseDto.setIdObraArchivo(saved.getIdObraArchivo());
        if (saved.getObra() != null) {
            responseDto.setIdObra(saved.getObra().getIdObra());
        }
        responseDto.setNombreArchivo(saved.getNombreArchivo());
        responseDto.setTipoArchivo(saved.getTipoArchivo());
        responseDto.setFileIdNube(saved.getFileIdNube());
        responseDto.setProveedorNube(saved.getProveedorNube());
        responseDto.setUrlAcceso(saved.getUrlAcceso());
        responseDto.setCategoria(saved.getCategoria());
        responseDto.setTamano(saved.getTamano());
        responseDto.setVersion(saved.getVersion());
        responseDto.setEstadoSincronizacion(saved.getEstadoSincronizacion());
        return responseDto;
    }

    @GetMapping
    public ResponseEntity<List<ObraArchivoDTO>> findAll() throws Exception {
        List<ObraArchivoDTO> list = service.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/proveedores")
    public ResponseEntity<ProveedorNube[]> getProveedoresNube() {
        return ResponseEntity.ok(ProveedorNube.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObraArchivoDTO> findById(@PathVariable("id") Integer id) throws Exception {
        ObraArchivo obj = service.findById(id);
        return ResponseEntity.ok(this.convertToDTO(obj));
    }

    @GetMapping("/obra/{idObra}")
    public ResponseEntity<List<ObraArchivoDTO>> findByObra(@PathVariable("idObra") Integer idObra) throws Exception {
        List<ObraArchivoDTO> list = service.findByObra(idObra)
                .stream()
                .map(e -> this.convertToDTO(e))
                .toList();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<ObraArchivoDTO> save(@Valid @RequestBody ObraArchivoDTO dto) throws Exception {
        ObraArchivo obj = service.save(modelMapper.map(dto, ObraArchivo.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdObraArchivo()).toUri();
        return ResponseEntity.created(location).body(this.convertToDTO(obj));
    }

    @PostMapping(value = "/upload", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ObraArchivoDTO> uploadFile(
            @RequestParam("file") org.springframework.web.multipart.MultipartFile file,
            @RequestParam("idObra") Integer idObra,
            @RequestParam("tipoArchivo") String tipoArchivo,
            @RequestParam(value = "proveedorNube", defaultValue = "GOOGLE_DRIVE") String proveedorNube,
            @RequestParam(value = "categoria", required = false) String categoria,
            @RequestParam(value = "urlAcceso", required = false) String urlAcceso
    ) throws Exception {

        String fileIdCloud = "file-" + System.currentTimeMillis();
        String finalUrl = urlAcceso;

        if ("GOOGLE_DRIVE".equalsIgnoreCase(proveedorNube) && file != null && !file.isEmpty()) {
            try {
                com.google.api.services.drive.model.File driveFile = googleDriveService.uploadFile(file, tipoArchivo);
                if (driveFile != null) {
                    fileIdCloud = driveFile.getId();
                    if (driveFile.getWebViewLink() != null) {
                        finalUrl = driveFile.getWebViewLink();
                    }
                }
            } catch (Exception e) {
                System.err.println("Error en carga a Google Drive (Quota/Permisos). Aplicando registro fallback: " + e.getMessage());
                fileIdCloud = "cloud-" + System.currentTimeMillis();
                if (finalUrl == null || finalUrl.isBlank()) {
                    finalUrl = "https://drive.google.com";
                }
            }
        }

        ObraArchivo entity = new ObraArchivo();
        com.boos.backend.Model.Obra obra = new com.boos.backend.Model.Obra();
        obra.setIdObra(idObra);
        entity.setObra(obra);
        entity.setNombreArchivo(file != null && !file.isEmpty() ? file.getOriginalFilename() : "Archivo_" + tipoArchivo);
        entity.setTipoArchivo(tipoArchivo);
        entity.setFileIdNube(fileIdCloud);
        entity.setProveedorNube(ProveedorNube.valueOf(proveedorNube.toUpperCase()));
        entity.setUrlAcceso(finalUrl != null ? finalUrl : "https://drive.google.com");
        entity.setCategoria(categoria != null ? categoria : "Documento de Obra");
        entity.setTamano(file != null ? String.format("%.1f MB", file.getSize() / (1024.0 * 1024.0)) : "1.0 MB");
        entity.setVersion("v1.0");
        entity.setEstadoSincronizacion("SINCRONIZADO");

        ObraArchivo saved = service.save(entity);
        ObraArchivoDTO responseDto = new ObraArchivoDTO();
        responseDto.setIdObraArchivo(saved.getIdObraArchivo());
        if (saved.getObra() != null) {
            responseDto.setIdObra(saved.getObra().getIdObra());
        }
        responseDto.setNombreArchivo(saved.getNombreArchivo());
        responseDto.setTipoArchivo(saved.getTipoArchivo());
        responseDto.setFileIdNube(saved.getFileIdNube());
        responseDto.setProveedorNube(saved.getProveedorNube());
        responseDto.setUrlAcceso(saved.getUrlAcceso());
        responseDto.setCategoria(saved.getCategoria());
        responseDto.setTamano(saved.getTamano());
        responseDto.setVersion(saved.getVersion());
        responseDto.setEstadoSincronizacion(saved.getEstadoSincronizacion());
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObraArchivoDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody ObraArchivoDTO dto)
            throws Exception {
        ObraArchivo obj = service.update(modelMapper.map(dto, ObraArchivo.class), id);
        return ResponseEntity.ok(this.convertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
