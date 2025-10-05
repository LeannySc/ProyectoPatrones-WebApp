package com.marketchasoy.web.modelo.patrones.singleton;


import java.util.UUID;

import com.marketchasoy.web.modelo.entidades.Usuario;

public class GestorSesion {
    // La única instancia de la clase (Singleton)
    private static GestorSesion instancia;
    private Usuario usuarioActual;
    private String idSesion;
    private boolean estaActiva;

    /**
     * Gestiona la sesión única del usuario actual.
     * Mantiene el estado de la sesión durante toda la aplicación.
     */
    private GestorSesion() {}

    public static synchronized GestorSesion obtenerInstancia() {
        if (instancia == null) {
            instancia = new GestorSesion();
        }
        return instancia;
    }

    public void crearSesion(Usuario usuario) {
        this.usuarioActual = usuario;
        this.idSesion = UUID.randomUUID().toString();
        this.estaActiva = true;
        System.out.println("Sesión creada para " + usuario.getNombre() + " con ID: " + idSesion);
    }

    public void destruirSesion() {
        this.usuarioActual = null;
        this.idSesion = null;
        this.estaActiva = false;
        System.out.println("Sesión destruida.");
    }

    public Usuario obtenerUsuarioActual() {
        return this.usuarioActual;
    }

    public boolean estaSesionActiva() {
        return this.estaActiva;
    }

    public void actualizarUltimaActividad() {
        System.out.println("Última actividad actualizada para la sesión: " + idSesion);
    }
}