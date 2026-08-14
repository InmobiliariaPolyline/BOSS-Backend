package com.boos.backend.Repository;

import com.boos.backend.Model.ObraArchivo;

import java.util.List;

public interface IObraArchivoRepository extends IGenericRepository<ObraArchivo, Integer> {
    List<ObraArchivo> findByObraIdObra(Integer idObra);
}
