package com.boos.backend.Service.Implementation;

import com.boos.backend.Model.ObraArchivo;
import com.boos.backend.Repository.IGenericRepository;
import com.boos.backend.Repository.IObraArchivoRepository;
import com.boos.backend.Service.IObraArchivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObraArchivoService extends GenericService<ObraArchivo, Integer> implements IObraArchivoService {

    private final IObraArchivoRepository repo;

    @Override
    protected IGenericRepository<ObraArchivo, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<ObraArchivo> findByObra(Integer idObra) throws Exception {
        return repo.findByObraIdObra(idObra);
    }
}
