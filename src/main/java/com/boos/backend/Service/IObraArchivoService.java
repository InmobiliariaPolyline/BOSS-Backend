package com.boos.backend.Service;

import com.boos.backend.Model.ObraArchivo;

import java.util.List;

public interface IObraArchivoService extends IGenericService<ObraArchivo, Integer> {
    List<ObraArchivo> findByObra(Integer idObra) throws Exception;
}
