package com.bodyhealth.controller;

import com.bodyhealth.model.*;
import com.bodyhealth.repository.*;
import com.bodyhealth.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@Slf4j
public class EntrenadorController {
    @Autowired
    private EntrenadorService entrenadorService;

    @Autowired
    private EntrenadorClienteRepository entrenadorClienteRepository;
    @Autowired
    private AdminService adminService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ControlClienteRepository controlClienteRepository;

    @Autowired
    private ClienteRutinaRepository clienteRutinaRepository;

    @Autowired
    private ControlClienteService controlClienteService;
    @Autowired
    private RutinaService rutinaService;

    @Autowired
    private ClienteRutinaService clienteRutinaService;

    @Autowired
    private ClienteRutinaEjercicioRepository clienteRutinaEjercicioRepository;

    @Autowired
    private RutinaEjercicioRepository rutinaEjercicioRepository;
    @Autowired
    private ClienteRutinaEjercicioService clienteRutinaEjercicioService;



    @GetMapping("/admin/dash-trainers")
    public String listarTrainers(Model model){

        List<Entrenador> activos = entrenadorService.listarActivos();
        List<Entrenador> desactivados = entrenadorService.listarDesactivados();
        model.addAttribute("trainers",activos);
        model.addAttribute("trainersDesactivados",desactivados);
        model.addAttribute("admins",adminService.listarAdministradores());

        return "/admin/trainers/dash-trainers";
    }

    @GetMapping("/admin/dash-trainers/expand/{id_entrenador}")
    public String mostrarEntrenador(Entrenador entrenador, Model model){


        Entrenador trainer = entrenadorService.encontrarEntrenador(entrenador);
        model.addAttribute("trainer",trainer);


        return "/admin/trainers/trainer-expand";
    }

    @GetMapping("/admin/dash-trainers/expand/editar/{id_entrenador}")
    public String editar(Entrenador entrenador, Model model){
        

        entrenador = entrenadorService.encontrarEntrenador(entrenador);

        model.addAttribute("trainer",entrenador);


        return "/admin/trainers/trainer-editar";
    }

    //Guarda edición de entrenador en el dashboard del admin
    @PostMapping("/admin/dash-trainers/expand/guardar")
    public String guardarEdicionEntrenador(Entrenador entrenador,@RequestParam("file") MultipartFile imagen){
        Path directorioImagenes = Paths.get("src//main//resources//static/images");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
        try {
            byte[] bytesImg = imagen.getBytes();
            Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
            Files.write(rutaCompleta, bytesImg);
            entrenador.setFoto(imagen.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        entrenadorService.guardar(entrenador);

        return "redirect:/admin/dash-trainers/expand/"+entrenador.getId_entrenador();
    }

    //Desactiva entrenadores en el dashboard del admin
    @GetMapping("/admin/dash-trainers/expand/desactivar/{id_entrenador}")
    public String desactivarEntrenador(Entrenador entrenador){

        entrenador = entrenadorService.encontrarEntrenador(entrenador);

        entrenador.setEstado(false);

        entrenadorService.guardar(entrenador);


        return "redirect:/admin/dash-trainers";
    }

    //Desactiva entrenadores en el dashboard del admin
    @GetMapping("/admin/dash-trainers/expand/activar/{id_entrenador}")
    public String activarEntrenador(Entrenador entrenador){

        entrenador = entrenadorService.encontrarEntrenador(entrenador);

        entrenador.setEstado(true);

        entrenadorService.guardar(entrenador);


        return "redirect:/admin/dash-trainers";
    }

    //ACCESO A TRAINER/RUTINAS

    @GetMapping("/trainer/dash-clientes")
    public String listarClientesEntrenador(Model model){

        List<EntrenadorCliente> clientesAsignados = entrenadorClienteRepository.encontrarClientes(1);
        log.info("ENTRE INICIO");


        model.addAttribute("clientesAsignados",clientesAsignados);

        return "/trainer/clientes/dash-clientes";
    }

    @GetMapping("/trainer/dash-clientes/expand/{id_cliente}")
    public String mostrarCliente(Cliente cliente, Model model){

        Cliente cnuevo = clienteService.encontrarCliente(cliente);
        ControlCliente control = controlClienteRepository.encontrarControlCliente(cnuevo.getId_cliente());
        ClienteRutina clienteRutina = clienteRutinaRepository.encontrarRutina(cnuevo.getId_cliente());
        List<Rutina> rutinas = rutinaService.listarRutina();


        //PARA EL CONTROL DE PESO Y ESTATURA
        ControlCliente controlNu = new ControlCliente();
        controlNu.setId_controlcliente(cnuevo.getId_cliente());
        controlNu.setPeso(-1);
        controlNu.setEstatura(-1);


        model.addAttribute("cliente",cnuevo);
        model.addAttribute("rutinas",rutinas);

        if(control != null){
            model.addAttribute("control",control);
        } else if(control == null){
            model.addAttribute("control",controlNu);
        }

        if(clienteRutina != null){
            log.info("IF ENVIO");
            model.addAttribute("clienteRutina",clienteRutina);

            //*GUARDA TODAS LOS EJERCICIOS DE LA RUTINA ESPECIFICADA EN LA TABLA CLIENTE_RUTINA_EJERCICIO
            List<ClienteRutinaEjercicio>  rutinaconejercicios = clienteRutinaEjercicioRepository.encontrarRutinaCompletaCliente(clienteRutina.getId_clienterutina());
            model.addAttribute("rutinaconejercicios",rutinaconejercicios);

            if(rutinaconejercicios.size()<=0){
                List<RutinaEjercicio> rutinaEjercicio = rutinaEjercicioRepository.encontrarRutinaEjercicios(clienteRutina.getId_rutina().getId_rutina());
                ClienteRutinaEjercicio clienteRutinaEjercicio;
                int idActual = clienteRutinaEjercicioRepository.idActual();
                for (int i = 1; i <= rutinaEjercicio.size(); i++) {
                    log.info("Ejecucion: "+i);
                    clienteRutinaEjercicio=new ClienteRutinaEjercicio();
                    clienteRutinaEjercicio.setId_cliente_rutina_ejercicio(idActual+i);
                    clienteRutinaEjercicio.setId_cliente_rutina(clienteRutina);
                    clienteRutinaEjercicio.setId_rutina_ejercicio(rutinaEjercicio.get(i-1));
                    clienteRutinaEjercicioService.guardar(clienteRutinaEjercicio);
                    log.info("ID ACTUAL: "+idActual+i);

                    log.info("Guardado: "+i);
                }
                //ACTUALIZADA
                model.addAttribute("rutinaconejercicios",clienteRutinaEjercicioRepository.encontrarRutinaCompletaCliente(clienteRutina.getId_clienterutina()));
            }
        } else if(clienteRutina == null){
            log.info("NO ENVIO");
            model.addAttribute("clienteRutina",clienteRutina);
        }




        return "/trainer/clientes/cliente-expand";
    }


    @PostMapping("/trainer/dash-clientes/guardar-control")
    public String guardarEdicionControl(ControlCliente controlCliente){

        log.info("CONTROL ENTRANTE: "+controlCliente.toString());

        controlClienteService.guardar(controlCliente);

        return "redirect:/trainer/dash-clientes/expand/"+controlCliente.getId_cliente().getId_cliente();
    }


    @PostMapping("/trainer/dash-rutinas/guardar-cliente-rutina")
    public String guardarAsignacionRutina(ClienteRutina clienteRutina){

        clienteRutinaService.guardar(clienteRutina);

        return "redirect:/trainer/dash-clientes/expand/"+clienteRutina.getId_cliente().getId_cliente();
    }

    @GetMapping("/trainer/dashboard")
    public String irDashboard(){

        return "/trainer/dashboard";
    }

    //M I P E R F I L
    @GetMapping("/trainer/perfil")
    public String perfilTrainer(Model model, Entrenador entrenador){

        entrenador.setId_entrenador(1);
        entrenador = entrenadorService.encontrarEntrenador(entrenador);

        model.addAttribute("trainer",entrenador);

        return "/trainer/perfil";
    }

    @GetMapping("/trainer/dash-trainer/ver-horario/{id_entrenador}")
    public String horarioTrainer(Model model, Entrenador entrenador){
        String rta="";

        entrenador = entrenadorService.encontrarEntrenador(entrenador);

        if(entrenador.getJornada().equalsIgnoreCase("Mañana")){
            rta = "/layouts/horario-manana";
        } else if (entrenador.getJornada().equalsIgnoreCase("Tarde")){
            rta = "/layouts/horario-tarde";
        }

        model.addAttribute("trainer",entrenador);
        return rta;
    }

    @PostMapping("/trainer/perfil/guardar-perfil")
    public String guardarEdicionPerfil(Entrenador entrenador,@RequestParam("file") MultipartFile imagen){

        Path directorioImagenes = Paths.get("src//main//resources//static/images");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
        try {
            byte[] bytesImg = imagen.getBytes();
            Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
            Files.write(rutaCompleta, bytesImg);
            entrenador.setFoto(imagen.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        entrenadorService.guardar(entrenador);

        return "redirect:/trainer/perfil";
    }

}
