package com.neoris.turnosrotativos.repository;
import com.neoris.turnosrotativos.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, Integer> {

    boolean existsByNroDocumento(Integer nroDocumento);
    boolean existsByEmail(String email);

}
