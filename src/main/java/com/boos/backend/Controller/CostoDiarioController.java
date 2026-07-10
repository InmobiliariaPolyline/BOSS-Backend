package com.boos.backend.Controller;

import com.boos.backend.Model.CostoDiario;
import com.boos.backend.Model.TipoCosto;
import com.boos.backend.Service.ICostoDiarioService;
import com.boos.backend.dto.CostoDiarioDTO;
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
@RequestMapping("/costos")
public class CostoDiarioController {
    private final ICostoDiarioService service;
    @Qualifier("detalleCostoMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CostoDiarioDTO>> findAll() throws Exception {
        List<CostoDiarioDTO> list = service.findAll().stream()
                .map(e -> modelMapper.map(e, CostoDiarioDTO.class)).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/tipos")
    public ResponseEntity<TipoCosto[]> getTiposCosto() {
        return ResponseEntity.ok(TipoCosto.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CostoDiarioDTO> findById(@PathVariable Integer id) throws Exception {
        CostoDiario obj = service.findById(id);
        return ResponseEntity.ok(modelMapper.map(obj, CostoDiarioDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CostoDiarioDTO dto) throws Exception {
        CostoDiario obj = service.save(modelMapper.map(dto, CostoDiario.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCosto()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CostoDiarioDTO> update(@PathVariable("id") Integer id, @RequestBody CostoDiarioDTO dto) throws Exception{
        CostoDiario obj = service.update(modelMapper.map(dto, CostoDiario.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, CostoDiarioDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
