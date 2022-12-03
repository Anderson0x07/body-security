package com.bodyhealth.controller;

import com.bodyhealth.model.ControlEntrenador;
import com.bodyhealth.model.Detalle;
import com.bodyhealth.service.ControlEntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/controlE")
public class ControlEntrenadorController {
    @Autowired
    private ControlEntrenadorService controlEntrenadorService;
    @GetMapping("/lista")
    public List<ControlEntrenador> listarPlanesDetallados(Model model){
        List<ControlEntrenador> controlEntrenadores = controlEntrenadorService.listarControlesE();
        model.addAttribute("controlEntrenadores",controlEntrenadores);
        return controlEntrenadores;
    }
}
