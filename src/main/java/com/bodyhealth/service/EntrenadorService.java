package com.bodyhealth.service;

import com.bodyhealth.model.Cliente;
import com.bodyhealth.model.Entrenador;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EntrenadorService {

    public List<Entrenador> listarEntrenadores();

    public void guardar(Entrenador entrenador);

    public void eliminar(Entrenador entrenador);

    public Entrenador encontrarEntrenador(Entrenador entrenador);

    public List<Entrenador> listarActivos();

    public List<Entrenador> listarDesactivados();
}
