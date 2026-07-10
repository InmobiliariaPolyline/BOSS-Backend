package com.boos.backend.Controller;

import com.boos.backend.Model.CajaChica;
import com.boos.backend.Model.GastoCaja;
import com.boos.backend.Service.IGastoCajaService;
import com.boos.backend.dto.GastoCajaDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/gasto-caja")
public class GastoCajaController {
    private final IGastoCajaService service;
    private final ModelMapper modelMapper;



    @GetMapping
    public ResponseEntity<List<GastoCaja>> findAll() throws Exception {
        List<GastoCaja> list = service.findAll().stream().map(e -> modelMapper.map(e, GastoCaja.class)).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GastoCaja> findById(@PathVariable Integer id) throws Exception {
        GastoCaja obj = service.findById(id);

        return ResponseEntity.ok(modelMapper.map(obj, GastoCaja.class));
    }


    @PostMapping
    public ResponseEntity<Void> save(@RequestBody GastoCajaDTO dto) throws Exception {
        GastoCaja obj = modelMapper.map(dto, GastoCaja.class);
        obj.setCajaChica(new CajaChica());
        obj.getCajaChica().setIdCajaChica(dto.getIdCajaChica());

        GastoCaja objGuardado = service.save(obj);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objGuardado.getIdGastoCaja()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GastoCaja> update(@PathVariable Integer id, @RequestBody GastoCaja dto) throws Exception {
        GastoCaja obj = service.update(modelMapper.map(dto, GastoCaja.class), id);

        return ResponseEntity.ok(modelMapper.map(obj, GastoCaja.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
