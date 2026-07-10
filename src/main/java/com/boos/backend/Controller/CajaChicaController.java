package com.boos.backend.Controller;
import com.boos.backend.Model.CajaChica;
import com.boos.backend.Service.ICajaChicaService;
import com.boos.backend.dto.CajaChicaDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

//@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/caja-chica")
@CrossOrigin(origins = "*")
public class CajaChicaController {
    private final ICajaChicaService service;
    private final ModelMapper modelMapper;


    @GetMapping
    public ResponseEntity<List<CajaChicaDTO>> findAll() throws Exception {
        List<CajaChicaDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, CajaChicaDTO.class)).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CajaChicaDTO> findById(@PathVariable Integer id) throws Exception {
        CajaChica obj = service.findById(id);

        return ResponseEntity.ok(modelMapper.map(obj, CajaChicaDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CajaChicaDTO dto) throws Exception {
        CajaChica obj = service.save(modelMapper.map(dto, CajaChica.class));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCajaChica()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CajaChicaDTO> update(@PathVariable Integer id, @RequestBody CajaChicaDTO dto) throws Exception {
        CajaChica obj = service.update(modelMapper.map(dto, CajaChica.class), id);

        return ResponseEntity.ok(modelMapper.map(obj, CajaChicaDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/hateoas/{id}")
    public EntityModel<CajaChicaDTO> findByIdHateoas(@PathVariable Integer id) throws Exception {
        CajaChica obj = service.findById(id);
        EntityModel<CajaChicaDTO> entityModel = EntityModel.of(modelMapper.map(obj, CajaChicaDTO.class));

        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CajaChicaController.class).findById(id));
        WebMvcLinkBuilder link2 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CajaChicaController.class).findAll());
        WebMvcLinkBuilder link3 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GastoCajaController.class).save(null));
        WebMvcLinkBuilder link4 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GastoCajaController.class).findAll());

        entityModel.add(link1.withRel("caja-chica-id-info"));
        entityModel.add(link2.withRel("caja-chica-all-info"));
        entityModel.add(link3.withRel("gasto-register"));
        entityModel.add(link4.withRel("gasto-all-info"));

        return entityModel;
    }
}
