package com.bodyhealth.controller;

import com.bodyhealth.model.Detalle;
import com.bodyhealth.model.Maquina;
import com.bodyhealth.service.DetalleService;
import com.bodyhealth.service.MaquinaService;
import com.bodyhealth.service.ProveedorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin")
public class MaquinaController {
    @Autowired
    private MaquinaService maquinaService;
    @Autowired
    private ProveedorService proveedorService;
    @GetMapping("/dash-maquinas")
    public String listarMaquinas(Model model){
        List<Maquina> maquinas = maquinaService.listarMaquinas();

        model.addAttribute("maquinas",maquinas);
        model.addAttribute("proveedores",proveedorService.listarProveedores());

        return "/admin/maquinas/dash-maquinas";
    }

    //Guarda nueva maquina
    @PostMapping("/dash-maquinas/guardar")
    public String guardarNuevaMaquina(Maquina maquina){

        log.info("MAQUINA: "+maquina.toString());


        maquinaService.guardar(maquina);

        return "redirect:/admin/dash-maquinas";
    }

    @GetMapping("/dash-maquinas/expand/{id_maquina}")
    public String expandMaquina(Maquina maquina, Model model){

        maquina = maquinaService.encontrarMaquina(maquina);

        model.addAttribute("maquina",maquina);

        return "/admin/maquinas/maquina-expand";
    }

    //Guarda edición de maquina en el dashboard del admin
    @PostMapping("/dash-maquinas/expand/guardar")
    public String guardarEdicionMaquina(Maquina maquina){

        maquinaService.guardar(maquina);

        return "redirect:/admin/dash-maquinas/expand/"+maquina.getId_maquina();
    }


    @GetMapping("/dash-maquinas/expand/editar/{id_maquina}")
    public String editar(Maquina maquina, Model model){

        maquina = maquinaService.encontrarMaquina(maquina);

        model.addAttribute("maquina",maquina);
        model.addAttribute("proveedores",proveedorService.listarProveedores());


        return "/admin/maquinas/maquina-editar";
    }

    @GetMapping("/dash-maquinas/eliminar")
    public String eliminarMaquina(Maquina maquina){
        maquinaService.eliminar(maquina);
        return "redirect:/admin/dash-maquinas";
    }
}
