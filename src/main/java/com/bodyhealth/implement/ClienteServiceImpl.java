package com.bodyhealth.implement;

import com.bodyhealth.model.Cliente;
import com.bodyhealth.repository.ClienteRepository;
import com.bodyhealth.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarClientes() {
        return (List<Cliente>) clienteRepo.findAll();
    }

    @Override
    @Transactional
    public void guardar(Cliente cliente) {
        clienteRepo.save(cliente);
    }

    @Override
    @Transactional
    public void eliminar(Cliente cliente) {
        clienteRepo.delete(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente encontrarCliente(Cliente cliente) {
        return clienteRepo.findById(cliente.getId_cliente()).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Cliente> listarActivos(){
        return (List<Cliente>) clienteRepo.findByEstado(true);
    }

    @Transactional(readOnly = true)
    public List<Cliente> listarDesactivados(){
        return (List<Cliente>) clienteRepo.findByEstado(false);
    }

}
