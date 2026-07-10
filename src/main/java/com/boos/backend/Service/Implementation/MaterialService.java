package com.boos.backend.Service.Implementation;

import com.boos.backend.Model.Material;
import com.boos.backend.Model.MovimientoMaterial;
import com.boos.backend.Repository.IMaterialRepository;
import com.boos.backend.Repository.IGenericRepository;
import com.boos.backend.Repository.IMovimientoMaterialRepository;
import com.boos.backend.Service.IMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MaterialService extends GenericService<Material, Integer> implements IMaterialService {
    private final IMaterialRepository repo;
    private final IMovimientoMaterialRepository movimientoRepo;

    @Override
    protected IGenericRepository<Material, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<Material> listPage(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Page<Material> listPageByObra(Integer idObra, Pageable pageable) {
        return repo.findByObraIdObra(idObra, pageable);
    }


    @Override
    @Transactional
    public Material save(Material material) throws Exception {
        boolean esNuevo = (material.getIdMaterial() == null || material.getIdMaterial() == 0);
        Material materialGuardado = repo.save(material);

        if (esNuevo) {
            MovimientoMaterial movimiento = new MovimientoMaterial();
            movimiento.setMaterial(materialGuardado);
            movimiento.setCantidad(materialGuardado.getStockActual().doubleValue());
            movimiento.setTipoMovimiento("ENTRADA");
            movimientoRepo.save(movimiento);
        }

        return materialGuardado;
    }

    @Override
    @Transactional
    public Material update(Material material, Integer id) throws Exception {
        Material materialActual = repo.findById(id)
                .orElseThrow(() -> new Exception("Material no encontrado con ID: " + id));

        double stockAnterior = materialActual.getStockActual().doubleValue();
        double stockNuevo    = material.getStockActual().doubleValue();
        double diferencia    = stockNuevo - stockAnterior;

        material.setIdMaterial(id);
        Material materialGuardado = repo.save(material);

        if (diferencia != 0) {
            MovimientoMaterial movimiento = new MovimientoMaterial();
            movimiento.setMaterial(materialGuardado);
            movimiento.setCantidad(Math.abs(diferencia));
            movimiento.setTipoMovimiento(diferencia > 0 ? "ENTRADA" : "SALIDA");
            movimientoRepo.save(movimiento);
        }

        return materialGuardado;
    }

    @Override
    @Transactional
    public void delete(Integer id) throws Exception {
        Material material = repo.findById(id)
                .orElseThrow(() -> new Exception("Material no encontrado con ID: " + id));
        movimientoRepo.deleteByMaterial(material);
        repo.deleteById(id);
    }
}