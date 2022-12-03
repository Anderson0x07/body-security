package com.bodyhealth.repository;

import com.bodyhealth.model.Cliente;
import com.bodyhealth.model.EntrenadorCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Integer>  {

    @Query(
            value = "SELECT * from cliente d where d.estado = :estado",
            nativeQuery=true
    )
    List<Cliente> findByEstado(@Param("estado") boolean estado);
}
