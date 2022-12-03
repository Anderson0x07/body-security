package com.bodyhealth.service;


import com.bodyhealth.model.Administrador;
import com.bodyhealth.model.Pedido;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AdminService {

    public List<Administrador> listarAdministradores();

    public void guardar(Administrador administrador);

    public void eliminar(Administrador administrador);

    public Administrador encontrarAdministrador(Administrador administrador);
}
