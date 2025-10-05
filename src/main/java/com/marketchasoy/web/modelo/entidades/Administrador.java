package com.marketchasoy.web.modelo.entidades;

import java.util.List;

import jakarta.persistence.Entity;


@Entity

public class Administrador extends Usuario {
    @Override
    public List<String> obtenerPermisos() {
        return List.of("BANEAR_USUARIOS", "APROBAR_PRODUCTOS", "GENERAR_REPORTES");
    }

    @Override
    public String obtenerTablero() {
        return "/dashboard/admin";
    }
    
    @Override
    public String obtenerRol() {
        return "Administrador";
    }

    public void banearUsuario(int idUsuario) {
        System.out.println("Usuario con ID " + idUsuario + " ha sido baneado.");
    }

    public void aprobarProducto(int idProducto) {
        System.out.println("Producto con ID " + idProducto + " ha sido aprobado.");
    }
}