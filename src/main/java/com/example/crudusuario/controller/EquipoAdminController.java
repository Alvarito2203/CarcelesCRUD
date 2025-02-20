package com.example.crudusuario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.crudusuario.model.Equipo;
import com.example.crudusuario.service.EquipoService;

@Controller
@RequestMapping("/admin/equipos")
public class EquipoAdminController {
    private final EquipoService equipoService;

    public EquipoAdminController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @GetMapping("/crear")
    public String crearForm(Model model) {
        model.addAttribute("equipo", new Equipo());
        return "equipo/crear";
    }

    @PostMapping
    public String guardar(@ModelAttribute Equipo equipo) {
        equipoService.agregarEquipo(equipo);
        return "redirect:/user/equipos";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Equipo equipo = equipoService.obtenerEquipoPorId(id);
        model.addAttribute("equipo", equipo);
        return "equipo/editar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Equipo equipo) {
        equipoService.actualizarEquipo(id, equipo);
        return "redirect:/user/equipos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        equipoService.eliminarEquipo(id);
        return "redirect:/user/equipos";
    }
}
