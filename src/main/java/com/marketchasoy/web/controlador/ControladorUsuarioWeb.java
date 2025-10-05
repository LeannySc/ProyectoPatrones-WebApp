package com.marketchasoy.web.controlador;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketchasoy.web.modelo.entidades.Usuario;
import com.marketchasoy.web.modelo.servicios.ServicioUsuario;

@Controller
public class ControladorUsuarioWeb {

    @Autowired
    private ServicioUsuario servicioUsuario;

    @GetMapping("/registro")
    public String mostrarFormularioDeRegistro() {
        return "registro-usuario";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@RequestParam String rol, @RequestParam String nombre, 
                                 @RequestParam String correo, @RequestParam String contrasena) {

        Map<String, Object> datos = new HashMap<>();
        datos.put("nombre", nombre);
        datos.put("correo", correo);
        datos.put("contrasena", contrasena);

        try {
            servicioUsuario.registrarNuevoUsuario(rol, datos);
        } catch (Exception e) {
            return "redirect:/registro?error";
        }
        
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarFormularioDeLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String correo, 
                              @RequestParam String contrasena, Model model) {

        Usuario usuario = servicioUsuario.autenticarUsuario(correo, contrasena);
        if (usuario != null) {
            return "redirect:/productos";
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos.");
            return "login";
        }
    }
}