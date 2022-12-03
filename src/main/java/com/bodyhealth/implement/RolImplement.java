package com.bodyhealth.implement;

import com.bodyhealth.model.Rol;
import com.bodyhealth.repository.RolRepository;
import com.bodyhealth.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolImplement implements RolService {
    @Autowired
    private RolRepository rolRepository;
    @Override
    public List<Rol> listarRoles() {
        return (List<Rol>) rolRepository.findAll();
    }

    @Override
    public void guardar(Rol rol) {
        rolRepository.save(rol);
    }

    @Override
    public void eliminar(Rol rol) {
        rolRepository.delete(rol);
    }

    @Override
    public Rol encontrarRol(Rol rol) {
        return rolRepository.findById(rol.getId_rol()).orElse(null);
    }
}
