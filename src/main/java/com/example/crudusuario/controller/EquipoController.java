package com.example.crudusuario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.crudusuario.service.EquipoService;
import com.example.crudusuario.model.Equipo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/equipo") // Prefijo de ruta para equipos
public class EquipoController {
    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    // Listar equipos
    @GetMapping("/index_equipos")
    public String listar(Model model) {
        model.addAttribute("equipos", equipoService.listarEquipos());
        return "equipo/index_equipos";
    }

    // Mostrar formulario de creación de equipo
    @GetMapping("/crear_equipo")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("equipo", new Equipo()); // Se pasa un objeto vacío
        return "equipo/crear_equipo";
    }

    // Guardar equipo
    @PostMapping("/guardar_equipo")
    public String guardar(@ModelAttribute Equipo equipo) {
        equipoService.agregarEquipo(equipo);
        return "redirect:/equipo/index_equipos"; // Redirección tras guardar
    }
    @GetMapping("/editar_equipo/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("equipo", equipoService.obtenerEquipoPorId(id));
        return "equipo/editar_equipo";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Equipo equipo) {
        equipoService.actualizarEquipo(id, equipo);
        return "redirect:/equipo/index_equipos";
    }
    @GetMapping("/eliminar_equipo/{id}")
    public String eliminar(@PathVariable Long id) {
        equipoService.eliminarEquipo(id);
        return "redirect:/equipo/index_equipos";
    }
    @GetMapping("/ver_futbolistas/{id}")
    public String verFutbolistas(@PathVariable Long id, Model model) {
        Equipo equipo = equipoService.obtenerEquipoPorId(id);
        if (equipo == null) {
            return "redirect:/equipo/index_equipos";  // Redirige si no encuentra el equipo
        }

        model.addAttribute("equipo", equipo);
        model.addAttribute("futbolistas", equipo.getFutbolistas());
        return "equipo/ver_futbolistas";
    }


    

}
