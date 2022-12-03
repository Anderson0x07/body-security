package com.bodyhealth.service;


import com.bodyhealth.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ClienteService {

    public List<Cliente> listarClientes();

    public void guardar(Cliente cliente);

    public void eliminar(Cliente cliente);

    public Cliente encontrarCliente(Cliente cliente);

    public List<Cliente> listarActivos();

    public List<Cliente> listarDesactivados();
}
