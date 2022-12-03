package com.bodyhealth.repository;

import com.bodyhealth.model.ClienteDetalle;
import com.bodyhealth.model.ClienteRutina;
import com.bodyhealth.model.ClienteRutinaEjercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRutinaRepository extends JpaRepository<ClienteRutina,Integer> {
    @Query(
            value = "SELECT * FROM cliente_rutina cr where cr.id_cliente = :id_cliente",
            nativeQuery=true
    )
    ClienteRutina encontrarRutina(@Param("id_cliente") int id_cliente);

}
