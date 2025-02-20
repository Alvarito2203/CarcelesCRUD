package com.example.crudusuario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.crudusuario.model.Futbolista;
import com.example.crudusuario.service.FutbolistaService;
import com.example.crudusuario.service.EquipoService;

@Controller
@RequestMapping("/admin/futbolistas")
public class FutbolistaAdminController {
    private final FutbolistaService futbolistaService;
    private final EquipoService equipoService;

    public FutbolistaAdminController(FutbolistaService futbolistaService, EquipoService equipoService) {
        this.futbolistaService = futbolistaService;
        this.equipoService = equipoService;
    }

    @GetMapping("/crear")
    public String crearForm(Model model) {
        model.addAttribute("futbolista", new Futbolista());
        model.addAttribute("equipos", equipoService.listarEquipos());
        return "futbolista/crear";
    }

    @PostMapping
    public String guardar(@ModelAttribute Futbolista futbolista) {
        futbolistaService.agregarFutbolista(futbolista);
        return "redirect:/user/futbolistas";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Futbolista futbolista = futbolistaService.obtenerFutbolistaPorId(id);
        model.addAttribute("futbolista", futbolista);
        model.addAttribute("equipos", equipoService.listarEquipos());
        return "futbolista/editar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Futbolista futbolista) {
        futbolistaService.actualizarFutbolista(id, futbolista);
        return "redirect:/user/futbolistas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        futbolistaService.eliminarFutbolista(id);
        return "redirect:/user/futbolistas";
    }
}


