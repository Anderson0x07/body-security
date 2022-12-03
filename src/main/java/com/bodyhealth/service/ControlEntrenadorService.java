package com.bodyhealth.service;

import com.bodyhealth.model.Cliente;
import com.bodyhealth.model.ControlEntrenador;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ControlEntrenadorService{

    public List<ControlEntrenador> listarControlesE();

    public void guardar(ControlEntrenador controlEntrenador);

    public void eliminar(ControlEntrenador controlEntrenador);

    public ControlEntrenador encontrarControlE(ControlEntrenador controlEntrenador);
}
