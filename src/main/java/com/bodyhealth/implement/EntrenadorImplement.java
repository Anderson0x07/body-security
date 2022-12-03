package com.bodyhealth.implement;

import com.bodyhealth.model.Cliente;
import com.bodyhealth.model.Entrenador;
import com.bodyhealth.repository.ClienteRepository;
import com.bodyhealth.repository.EntrenadorRepository;
import com.bodyhealth.service.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Access;
import java.util.List;

@Service
public class EntrenadorImplement implements EntrenadorService {

    @Autowired
    private EntrenadorRepository entrenadorRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Entrenador> listarEntrenadores() {
        return entrenadorRepo.findAll();
    }

    @Override
    @Transactional
    public void guardar(Entrenador entrenador) {
        entrenadorRepo.save(entrenador);
    }

    @Override
    @Transactional
    public void eliminar(Entrenador entrenador) {
        entrenadorRepo.delete(entrenador);
    }

    @Override
    @Transactional(readOnly = true)
    public Entrenador encontrarEntrenador(Entrenador entrenador) {
        return entrenadorRepo.findById(entrenador.getId_entrenador()).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Entrenador> listarActivos(){
        return (List<Entrenador>) entrenadorRepo.findByEstado(true);
    }

    @Transactional(readOnly = true)
    public List<Entrenador> listarDesactivados(){
        return (List<Entrenador>) entrenadorRepo.findByEstado(false);
    }
}
