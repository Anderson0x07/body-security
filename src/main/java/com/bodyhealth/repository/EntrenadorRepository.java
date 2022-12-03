package com.bodyhealth.repository;

import com.bodyhealth.model.Cliente;
import com.bodyhealth.model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntrenadorRepository extends JpaRepository<Entrenador,Integer> {

    @Query(
            value = "SELECT * from entrenador e where e.estado = :estado",
            nativeQuery=true
    )
    List<Entrenador> findByEstado(@Param("estado") boolean estado);

    @Query(
            value = "SELECT * from entrenador e where e.jornada = :jornada",
            nativeQuery=true
    )
    List<Entrenador> entrenadoresJornada(@Param("jornada") String jornada);

}
