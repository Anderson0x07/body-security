package com.bodyhealth.controller;


import com.bodyhealth.model.*;
import com.bodyhealth.repository.*;
import com.bodyhealth.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EntrenadorClienteRepository entrenadorClienteRepository;
    @Autowired
    private ClienteDetalleRepository clienteDetalleRepository;

    @Autowired
    private DetalleRepository detalleRepository;

    @Autowired
    private EntrenadorClienteService entrenadorClienteService;
    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private ClienteDetalleService clienteDetalleService;

    @Autowired
    private RolRepository rolRepository;
    @Autowired
    ProductoService productoService;

    @Autowired
    private ControlClienteRepository controlClienteRepository;
    @Autowired
    private ClienteRutinaRepository clienteRutinaRepository;

    @Autowired
    private RutinaService rutinaService;
    @Autowired
    private ClienteRutinaEjercicioRepository clienteRutinaEjercicioRepository;
    @Autowired
    private RutinaEjercicioRepository rutinaEjercicioRepository;
    @Autowired
    private ClienteRutinaEjercicioService clienteRutinaEjercicioService;
    @Autowired
    private ControlClienteService controlClienteService;

    @Autowired
    private ClienteRutinaService clienteRutinaService;






    //Lista clientes en el dashboard del admin
    @GetMapping("/admin/dash-clientes")
    public String listarClientes(Model model){

        List<Cliente> activos = clienteService.listarActivos();
        List<Cliente> desactivados = clienteService.listarDesactivados();
        model.addAttribute("clientes",activos);
        model.addAttribute("clientesDesactivados",desactivados);
        model.addAttribute("rol",rolRepository.getReferenceById(2));

        return "/admin/clientes/dash-clientes";
    }

    @GetMapping("/admin/dash-clientes/expand/{id_cliente}")
    public String mostrarCliente(Cliente cliente, Model model){

        log.info("CLIENTE QUE LLEGA: "+cliente.toString());

        Cliente cnuevo = clienteService.encontrarCliente(cliente);
        model.addAttribute("cliente",cnuevo);

        EntrenadorCliente entrenadorCliente = entrenadorClienteRepository.encontrarEntrenador(cnuevo.getId_cliente());

        //ENTRENADOR DEL CLIENTE EN CASO DE TENER
        if(entrenadorCliente!=null){
            model.addAttribute("entrenadorcliente",entrenadorCliente);
        }

        //PLAN DE CLIENTE EN CASO DE TENER
        ClienteDetalle clienteDetalle = clienteDetalleRepository.encontrarPlan(cnuevo.getId_cliente());

        if(clienteDetalle!=null){
            log.info("CLIENTE PLAN XD: "+clienteDetalle.toString());
            model.addAttribute("clientedetalle",clienteDetalle);
        }

        //PARA EL CONTROL DE PESO Y ESTATURA
        ControlCliente control = controlClienteRepository.encontrarControlCliente(cnuevo.getId_cliente());
        if(control != null){
            model.addAttribute("control",control);
        }

        ClienteRutina clienteRutina = clienteRutinaRepository.encontrarRutina(cnuevo.getId_cliente());

        List<Rutina> rutinas = rutinaService.listarRutina();
        model.addAttribute("rutinas",rutinas);
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
        }




        return "/admin/clientes/cliente-expand";
    }

    //Guarda clientes en el dashboard del admin
    @PostMapping("/admin/dash-clientes/expand/guardar")
    public String guardarCliente(Cliente cliente,@RequestParam("file") MultipartFile imagen){
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

        return "redirect:/admin/dash-clientes";
    }


    //ACTUALIZA ENTRENADOR A CLIENTE
    @PostMapping("/admin/dash-clientes/expand/guardartrainer")
    public String cambioEntrenador(EntrenadorCliente entrenadorCliente, Model model){

        int id_cliente = entrenadorCliente.getCliente().getId_cliente();

        entrenadorClienteService.guardar(entrenadorCliente);

        model.addAttribute("trainer",entrenadorClienteRepository.encontrarEntrenador(entrenadorCliente.getCliente().getId_cliente()));

        return "redirect:/admin/dash-clientes/expand/editar/"+id_cliente;
    }

    //ACTUALIZA PLAN DEL CLIENTE
    @PostMapping("/admin/dash-clientes/expand/guardarPlan")
    public String cambioPlan(ClienteDetalle clienteDetalle, Model model){

        log.info("Cambio o add de plan: "+clienteDetalle.toString());

        int id_cliente = clienteDetalle.getId_cliente().getId_cliente();

        clienteDetalleService.guardar(clienteDetalle);

        model.addAttribute("clienteDetalle",clienteDetalleRepository.encontrarPlan(clienteDetalle.getId_cliente().getId_cliente()));

        return "redirect:/admin/dash-clientes/expand/editar/"+id_cliente;
    }



    //Editar clientes en el dashboard del admin
    @GetMapping("/admin/dash-clientes/expand/editar/{id_cliente}")
    public String editarCliente(Cliente cliente, Model model){
    log.info("entre...");
        cliente = clienteService.encontrarCliente(cliente);
        model.addAttribute("cliente",cliente);

        //ENCONTRAR ENTRENADOR ASIGNADO EN CASO DE TENER UNO
        EntrenadorCliente entrenadorCliente = entrenadorClienteRepository.encontrarEntrenador(cliente.getId_cliente());
        if(entrenadorCliente!=null){
            model.addAttribute("trainer",entrenadorCliente);
        }
        //PARA MOSTRAR TODOS LOS ENTRENADORES POR JORNADA
        List<Entrenador> entrenadoresJornada = entrenadorRepository.entrenadoresJornada(cliente.getJornada());
        if(entrenadoresJornada.size()>0){
            model.addAttribute("trainers",entrenadoresJornada);
        }

        //PARA MOSTRAR PLAN ASIGNADO EN CASO DE TENER UNO
        ClienteDetalle clienteDetalle = clienteDetalleRepository.encontrarPlan(cliente.getId_cliente());
        if(clienteDetalle!=null){
            model.addAttribute("clienteDetalle",clienteDetalle);
        }
        //PARA MOSTRAR TODOS LOS PLANES PARA SELECCIONAR
        model.addAttribute("planesdetallados",detalleRepository.findAll());
        //PARA MOSTRAR TODOS LOS METODOS DE PAGO DISPONIBLES
        model.addAttribute("metodos",metodoPagoRepository.findAll());


        return "/admin/clientes/cliente-editar";
    }

    @PostMapping("/admin/dash-clientes/expand/guardar-control")
    public String guardarEdicionControl(ControlCliente controlCliente){

        log.info("CONTROL ENTRANTE: "+controlCliente.toString());

        controlClienteService.guardar(controlCliente);

        return "redirect:/admin/dash-clientes/expand/"+controlCliente.getId_cliente().getId_cliente();
    }
    @PostMapping("/admin/dash-clientes/expand/guardar-cliente-rutina")
    public String guardarAsignacionRutina(ClienteRutina clienteRutina){

        clienteRutinaService.guardar(clienteRutina);

        return "redirect:/admin/dash-clientes/expand/"+clienteRutina.getId_cliente().getId_cliente();
    }

    //Desactiva clientes en el dashboard del admin
    @GetMapping("/admin/dash-clientes/expand/desactivar/{id_cliente}")
    public String desactivarCliente(Cliente cliente){

        cliente = clienteService.encontrarCliente(cliente);
        log.info(cliente.toString());
        cliente.setEstado(false);

        clienteService.guardar(cliente);


        return "redirect:/admin/dash-clientes";
    }
    //Desactiva clientes en el dashboard del admin
    @GetMapping("/admin/dash-clientes/expand/activar/{id_cliente}")
    public String activarCliente(Cliente cliente){

        cliente = clienteService.encontrarCliente(cliente);

        cliente.setEstado(true);

        clienteService.guardar(cliente);


        return "redirect:/admin/dash-clientes";
    }




}
