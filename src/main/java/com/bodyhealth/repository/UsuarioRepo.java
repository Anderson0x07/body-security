package com.bodyhealth.repository;

import com.bodyhealth.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario,Integer> {
    Usuario findByNombre(String nombre);
}
