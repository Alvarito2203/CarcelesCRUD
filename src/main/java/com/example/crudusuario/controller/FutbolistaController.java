package com.example.crudusuario.controller;

import org.springframework.stereotype.Controller;
import com.example.crudusuario.service.EquipoService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.crudusuario.model.Futbolista;
import com.example.crudusuario.service.FutbolistaService;

@Controller
@RequestMapping("/persona") // Prefijo de ruta para futbolistas
public class FutbolistaController {
    private final FutbolistaService futbolistaService;
    private final EquipoService equipoService; // Agrega esta línea


    public FutbolistaController(FutbolistaService futbolistaService, EquipoService equipoService) { 
        this.futbolistaService = futbolistaService;
        this.equipoService = equipoService; // Inicializa equipoService
    }

    // Listar futbolistas
    @GetMapping("/index_futbolistas")
    public String listar(Model model) {
        model.addAttribute("futbolistas", futbolistaService.listarFutbolistas());
        return "persona/index_futbolistas"; // Devuelve la vista en "persona"
    }

    @GetMapping("/crear_futbolista")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("futbolista", new Futbolista());
        model.addAttribute("equipos", equipoService.listarEquipos()); // Cargar equipos
        return "persona/crear_futbolista";
    }


    @PostMapping("/guardar_futbolista")
    public String guardar(@ModelAttribute Futbolista futbolista) {
        // Verifica que el equipo no sea null antes de guardar
        if (futbolista.getEquipo() != null) {
            System.out.println("Guardando futbolista con equipo: " + futbolista.getEquipo().getNombre());
        } else {
            System.out.println("Guardando futbolista sin equipo asignado");
        }
        
        futbolistaService.agregarFutbolista(futbolista);
        return "redirect:/persona/index_futbolistas";
    }


    @GetMapping("/editar_futbolista/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Futbolista futbolista = futbolistaService.obtenerFutbolistaPorId(id);
        model.addAttribute("futbolista", futbolista);
        model.addAttribute("equipos", equipoService.listarEquipos()); // Para seleccionar equipo
        return "persona/editar_futbolista";
    }


    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Futbolista futbolista) {
        futbolistaService.actualizarFutbolista(id, futbolista);
        return "redirect:/persona/index_futbolistas";
    }


    @GetMapping("/eliminar_futbolista/{id}")
    public String eliminar(@PathVariable Long id) {
        futbolistaService.eliminarFutbolista(id);
        return "redirect:/persona/index_futbolistas";
    }

}
