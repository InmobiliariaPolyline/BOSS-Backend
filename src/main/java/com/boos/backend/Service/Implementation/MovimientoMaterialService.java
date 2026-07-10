package com.boos.backend.Service.Implementation;

import com.boos.backend.Model.MovimientoMaterial;
import com.boos.backend.Repository.IMovimientoMaterialRepository;
import com.boos.backend.Repository.IGenericRepository;
import com.boos.backend.Service.IMovimientoMaterialService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovimientoMaterialService extends GenericService<MovimientoMaterial, Integer> implements IMovimientoMaterialService {

    private final IMovimientoMaterialRepository repo;

    @Override
    protected IGenericRepository<MovimientoMaterial, Integer> getRepo() {
        return repo;
    }
    @Override
    @Transactional
    public MovimientoMaterial update(MovimientoMaterial movimiento, Integer id) throws Exception {

        MovimientoMaterial existente = repo.findById(id)
                .orElseThrow(() -> new Exception("Movimiento no encontrado"));

        existente.setCantidad(movimiento.getCantidad());
        existente.setTipoMovimiento(movimiento.getTipoMovimiento());

        return repo.save(existente);
    }
}
