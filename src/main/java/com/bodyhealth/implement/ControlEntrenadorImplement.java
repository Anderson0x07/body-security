package com.bodyhealth.implement;


import com.bodyhealth.model.ControlEntrenador;
import com.bodyhealth.repository.ControlEntrenadorRepository;
import com.bodyhealth.service.ControlEntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControlEntrenadorImplement implements ControlEntrenadorService {

    @Autowired
    private ControlEntrenadorRepository controlEntrenadorRepository;

    @Override
    public List<ControlEntrenador> listarControlesE() {
        return (List<ControlEntrenador>) controlEntrenadorRepository.findAll();
    }

    @Override
    public void guardar(ControlEntrenador controlEntrenador) {
        controlEntrenadorRepository.save(controlEntrenador);
    }

    @Override
    public void eliminar(ControlEntrenador controlEntrenador) {
        controlEntrenadorRepository.delete(controlEntrenador);
    }

    @Override
    public ControlEntrenador encontrarControlE(ControlEntrenador controlEntrenador) {
        return controlEntrenadorRepository.findById(controlEntrenador.getDocumentoE()).orElse(null);
    }
}
