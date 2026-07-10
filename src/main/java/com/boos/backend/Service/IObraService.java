package com.boos.backend.Service;

import com.boos.backend.Model.Obra;

public interface IObraService extends IGenericService<Obra, Integer> {

    void agregarEmpleado(Integer idObra, Integer idEmpleado) throws Exception;
}
