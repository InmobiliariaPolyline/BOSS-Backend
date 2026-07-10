package com.boos.backend.Service.Implementation;

import com.boos.backend.Model.CostoDiario;
import com.boos.backend.Repository.ICostoDiarioRepository;
import com.boos.backend.Repository.IGenericRepository;
import com.boos.backend.Service.ICostoDiarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CostoDiarioService extends GenericService<CostoDiario, Integer> implements ICostoDiarioService {

    private final ICostoDiarioRepository repo;
    @Override
    protected IGenericRepository<CostoDiario, Integer> getRepo() {
        return repo;
    }
}
