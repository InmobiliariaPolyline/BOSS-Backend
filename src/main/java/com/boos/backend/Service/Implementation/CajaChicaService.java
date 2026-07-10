package com.boos.backend.Service.Implementation;

import com.boos.backend.Model.CajaChica;
import com.boos.backend.Repository.ICajaChicaRepository;
import com.boos.backend.Repository.IGenericRepository;
import com.boos.backend.Service.ICajaChicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CajaChicaService extends GenericService<CajaChica,Integer> implements ICajaChicaService {
    private final ICajaChicaRepository repo;
    @Override
    protected IGenericRepository<CajaChica, Integer> getRepo() {
        return repo;
    }

}
