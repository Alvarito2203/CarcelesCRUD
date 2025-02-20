package com.example.crudusuario.controller;

import com.example.crudusuario.model.Usuario;
import com.example.crudusuario.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UsuarioService usuarioService;

    public AdminController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/dashboard")
    public String showAdminDashboard(Model model) {
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "admin/dashboard";  
    }

    @GetMapping("/usuarios/nuevo")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "admin/registro_usuario";
    }

    @PostMapping("/usuarios/guardar")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.registrarUsuario(usuario);
        return "redirect:/admin/dashboard";
    }

  

    @PostMapping("/admin/usuarios/actualizar/{id}")
    public String actualizarUsuario(@PathVariable Long id, @ModelAttribute("usuario") Usuario usuarioActualizado) {
        usuarioService.actualizarUsuario(id, usuarioActualizado);
        return "redirect:/admin/usuarios/listar";
    }

    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return "redirect:/admin/dashboard";
        
    }
    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "admin/listar_usuarios";
    }
    @PostMapping("/usuarios/actualizar")
    public String actualizarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.registrarUsuario(usuario);  // Guarda directamente sin verificar la contraseña
        return "redirect:/admin/usuarios";
    }


}
