package com.boos.backend.Service.Implementation;

import com.boos.backend.Model.GastoCaja;
import com.boos.backend.Repository.IGastoCajaRepository;
import com.boos.backend.Repository.IGenericRepository;
import com.boos.backend.Service.IGastoCajaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GastoCajaService extends GenericService<GastoCaja, Integer> implements IGastoCajaService {
    private final IGastoCajaRepository repo;

    @Override
    protected IGenericRepository<GastoCaja, Integer> getRepo() {
        return repo;
    }
}
