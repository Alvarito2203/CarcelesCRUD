package com.example.crudusuario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    @GetMapping("/")
    public String redirigirAlLogin() {
        return "redirect:/login"; // Redirige la raíz al login
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // Devuelve la vista login.html
    }

    @GetMapping("/home")
    public String mostrarHome() {
        return "user/home"; // Devuelve la vista home.html
    }

   
}
