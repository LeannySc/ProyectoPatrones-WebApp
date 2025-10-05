package com.marketchasoy.web.modelo.entidades;

import java.util.Date;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public abstract class Usuario {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)

    protected int id;
    protected String correo;
    protected String contrasena;
    protected String nombre;
    protected Date fechaCreacion;

    public abstract List<String> obtenerPermisos();

    public abstract String obtenerTablero();

    public abstract String obtenerRol();

    // --- SETTERS PÚBLICOS PARA PERMITIR LA MODIFICACIÓN ---
    public void setId(int id) {
        this.id = id;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    // GETTERS PÚBLICOS PARA PERMITIR ACCESO DE SÓLO LECTURA

    // Ya que estamos aquí, añadamos los otros por si los necesitamos luego
    public int getId() {
        return this.id;
    }

    public String getCorreo() {
        return this.correo;
    }

    // Métodos comunes
    public boolean iniciarSesion(String contrasena) {
        return this.contrasena.equals(contrasena);
    }

    public void cerrarSesion() {
        System.out.println(nombre + " ha cerrado sesión.");
    }

    public void actualizarPerfil() {
        System.out.println("Perfil de " + nombre + " actualizado.");
    }

    public int obtenerId() {
        return id;
    }

    public String obtenerCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }
    public String getContrasena(){
        return this.contrasena;
    }
}