package com.boos.backend.Controller;

import com.boos.backend.Model.Donacion;
import com.boos.backend.Model.Obra;
import com.boos.backend.Service.IDonacionService;
import com.boos.backend.dto.DonacionesDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/donaciones")
public class DonacionController {
    private final IDonacionService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<DonacionesDTO>> findAll() throws Exception {
        List<DonacionesDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, DonacionesDTO.class)).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonacionesDTO> findById(@PathVariable Integer id) throws Exception {
        Donacion obj = service.findById(id);

        return ResponseEntity.ok(modelMapper.map(obj, DonacionesDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody DonacionesDTO dto) throws Exception {

        Donacion obj = modelMapper.map(dto, Donacion.class);
        obj.setObra(new Obra());
        obj.getObra().setIdObra(dto.getIdObra());
        obj.setFechaRegistro(LocalDate.now());

        Donacion objGuardado = service.save(obj);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objGuardado.getIdDonacion()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<DonacionesDTO> update(@PathVariable Integer id, @RequestBody DonacionesDTO dto) throws Exception {
        Donacion obj = service.update(modelMapper.map(dto, Donacion.class), id);

        return ResponseEntity.ok(modelMapper.map(obj, DonacionesDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/hateoas/{id}")
    public EntityModel<DonacionesDTO> findByIdHateoas(@PathVariable Integer id) throws Exception {
        Donacion obj = service.findById(id);
        EntityModel<DonacionesDTO> entityModel = EntityModel.of(modelMapper.map(obj, DonacionesDTO.class));

        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DonacionController.class).findAll());
        WebMvcLinkBuilder link2 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DonacionController.class).save(null));

        entityModel.add(link1.withRel("donaciones-all-info"));
        entityModel.add(link2.withRel("donacion-register"));

        return entityModel;
    }
}
