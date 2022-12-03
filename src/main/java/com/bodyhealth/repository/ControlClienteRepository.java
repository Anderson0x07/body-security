package com.bodyhealth.repository;

import com.bodyhealth.model.ControlCliente;
import com.bodyhealth.model.EntrenadorCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.naming.ldap.Control;
import java.util.List;

public interface ControlClienteRepository extends JpaRepository<ControlCliente,Integer> {

    @Query(
            value = "SELECT * from control_cliente c where c.id_cliente = :id_cliente ORDER BY c.fecha DESC limit 1",
            nativeQuery = true
    )
    ControlCliente encontrarControlCliente(@Param("id_cliente") int id_cliente);

}
