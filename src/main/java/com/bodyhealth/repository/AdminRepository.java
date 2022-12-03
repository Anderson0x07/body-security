package com.bodyhealth.repository;

import com.bodyhealth.model.Administrador;
import com.bodyhealth.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<Administrador,Integer>  {

    @Query(
            value = "SELECT * from administrador a where a.email = :email",
            nativeQuery=true
    )
    Administrador findByEmail(@Param("email") String email);


}
