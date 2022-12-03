package com.bodyhealth.service;

import com.bodyhealth.model.Cliente;
import com.bodyhealth.model.Rol;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RolService {
    public List<Rol> listarRoles();

    public void guardar(Rol rol);

    public void eliminar(Rol rol);

    public Rol encontrarRol(Rol rol);
}
