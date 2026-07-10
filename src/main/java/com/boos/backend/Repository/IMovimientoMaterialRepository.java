package com.boos.backend.Repository;

import com.boos.backend.Model.Material;
import com.boos.backend.Model.MovimientoMaterial;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IMovimientoMaterialRepository extends IGenericRepository<MovimientoMaterial, Integer> {
    @Modifying
    @Query("DELETE FROM MovimientoMaterial m WHERE m.material = :material")
    void deleteByMaterial(@Param("material") Material material);
}
