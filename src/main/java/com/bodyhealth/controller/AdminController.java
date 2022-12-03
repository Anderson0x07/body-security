package com.bodyhealth.controller;


import com.bodyhealth.model.Administrador;
import com.bodyhealth.model.Cliente;
import com.bodyhealth.model.Entrenador;
import com.bodyhealth.repository.AdminRepository;
import com.bodyhealth.service.AdminService;
import com.bodyhealth.service.ClienteService;
import com.bodyhealth.service.EntrenadorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@Slf4j
public class AdminController {

    //redirect: Hace peticion a un getMapping
    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRepository adminRepository;

    //Para acceder al dashboard
    @GetMapping("/admin/dashboard")
    public String inicio(Model model){

        log.info("dashboard");
        model.addAttribute("admin");

        return "admin/dashboard";
    }

    @GetMapping("/admin/dash-horarios")
    public String listarHorarios(){
        return "admin/dash-horarios";
    }


    //M I P E R F I L
    @GetMapping("/admin/perfil")
    public String perfilAdmin(Model model, @AuthenticationPrincipal User user){

        Administrador administrador = adminRepository.findByEmail(user.getUsername());

        model.addAttribute("admin",administrador);

        return "admin/perfil";
    }


    @PostMapping("/admin/perfil/guardar-perfil")
    public String guardarEdicionPerfil(Administrador administrador){

        adminService.guardar(administrador);

        return "redirect:/admin/perfil";
    }



}
