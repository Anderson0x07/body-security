package com.bodyhealth.controller;

import com.bodyhealth.model.Cliente;
import com.bodyhealth.model.Producto;
import com.bodyhealth.repository.UsuarioRepo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.bodyhealth.service.ClienteService;
import com.bodyhealth.service.ProductoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
public class ControladorInicio {
    
    @Autowired
    private UsuarioRepo rep;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/")
    public String index(){
        return "/index";
    }

    @GetMapping("/productos")
    public String productos(Model model){
        List<Producto> product = productoService.listarProductos();
        model.addAttribute("productos",product);
        return "/cliente/productos";
    }

    @GetMapping("/planes")
    public String planes(){
        return "/cliente/planes";
    }
    @GetMapping("/noticias")
    public String noticias(){
        return "/cliente/noticias";
    }
    @GetMapping("/registro-cliente")
    public String registroCliente(){
        return "/cliente/registro-cliente";
    }
    @GetMapping("/login1")
    public String login(){
        return "login";
    }
    //Prueba de nuevo regstro cliente plantilla
    @GetMapping("/registroCliente")
    public String registroClientePlantilla(){
        return "/cliente/registroCliente";
    }
    @PostMapping("/dash-clientes/expand/guardar")
    public String guardarClienteIndex(Cliente cliente, @RequestParam("file") MultipartFile imagen){
        Path directorioImagenes = Paths.get("src//main//resources//static/images");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
        try {
            byte[] bytesImg = imagen.getBytes();
            Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
            Files.write(rutaCompleta, bytesImg);
            cliente.setFoto(imagen.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("LLEGÓ: "+cliente.getDocumentoC());
        clienteService.guardar(cliente);

        return "login";
    }

    @GetMapping("/administradores")
    public String administrador(){
        log.info("ENTRO A ADMINS");
        return "/admin/dashboard";
    }

    @GetMapping("/trainer")
    public String trainers(){
        log.info("ENTRO A TRAINER");
        return "/trainer/dashboard";
    }

    @GetMapping("/errores/403")
    public String muestraAccesoDenegado(){
        log.info("ENTRO A errores");
        return "/errores/403";
    }
    

}
