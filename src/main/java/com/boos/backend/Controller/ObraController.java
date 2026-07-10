package com.boos.backend.Controller;

import com.boos.backend.Model.Obra;
import com.boos.backend.dto.ObraDTO;
import com.boos.backend.Service.IObraService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/obra")
@CrossOrigin(origins = "*")
public class ObraController {
    private final IObraService service;
    @Qualifier("obraMapper")
    private final ModelMapper obraMapper;

    @GetMapping
    public ResponseEntity<List<ObraDTO>> findAll() throws Exception {
        List<ObraDTO> list = service.findAll().stream().map(o -> obraMapper.map(o, ObraDTO.class)).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObraDTO> findById(@PathVariable("id") Integer id) throws Exception {
        Obra obj = service.findById(id);
        return ResponseEntity.ok(obraMapper.map(obj, ObraDTO.class));
    }

    @PostMapping
    public ResponseEntity<ObraDTO> save(@RequestBody ObraDTO dto) throws Exception {
        Obra obra = obraMapper.map(dto, Obra.class);
        Obra obj = service.save(obra);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdObra())
                .toUri();
        return ResponseEntity.created(location).body(obraMapper.map(obj, ObraDTO.class));
    }


    @PostMapping("/{idObra}/empleados")
    public ResponseEntity<Void> agregarEmpleado(
            @PathVariable Integer idObra,
            @RequestBody Map<String, Integer> body) throws Exception {
        service.agregarEmpleado(idObra, body.get("idEmpleado"));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObraDTO> update(@PathVariable("id") Integer id, @RequestBody ObraDTO dto) throws Exception {
        Obra obra = obraMapper.map(dto, Obra.class);
        Obra obj = service.update(obra, id);
        return ResponseEntity.ok(obraMapper.map(obj, ObraDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
