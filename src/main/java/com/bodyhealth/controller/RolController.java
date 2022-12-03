package com.bodyhealth.controller;

import com.bodyhealth.model.Proveedor;
import com.bodyhealth.model.Rol;
import com.bodyhealth.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rol")
public class RolController {
    @Autowired
    private RolService rolService;
    @GetMapping("/lista")
    public List<Rol> listarRutinas(Model model){
        List<Rol> roles = rolService.listarRoles();

        model.addAttribute("roles",roles);

        return roles;
    }
}
