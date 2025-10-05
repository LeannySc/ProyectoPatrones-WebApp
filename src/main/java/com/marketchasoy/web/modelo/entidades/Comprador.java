package com.marketchasoy.web.modelo.entidades;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Comprador extends Usuario {

    private String direccionEnvio;
    
    // --- AQUÍ VA LA ANOTACIÓN, ENCIMA DEL CAMPO ---
    @OneToMany(mappedBy = "comprador", cascade = CascadeType.ALL)
    private List<Pedido> historialPedidos = new ArrayList<>();

    // ... (El resto de tus métodos: obtenerPermisos, agregarAlCarrito, etc. están bien)

    @Override
    public List<String> obtenerPermisos() {
        return List.of("VER_PRODUCTOS", "COMPRAR", "VER_HISTORIAL");
    }

    @Override
    public String obtenerTablero() {
        return "/dashboard/comprador";
    }

    @Override
    public String obtenerRol() {
        return "Comprador";
    }

    public void agregarAlCarrito(Producto producto) {
        System.out.println("Producto " + producto.getNombre() + " agregado al carrito.");
    }

    public Pedido finalizarCompra() {
        System.out.println("Compra finalizada.");
        return new Pedido(100.0f); // Monto de ejemplo
    }

    // --- GETTERS Y SETTERS PARA LA RELACIÓN ---
    public List<Pedido> getHistorialPedidos() {
        return historialPedidos;
    }

    public void setHistorialPedidos(List<Pedido> historialPedidos) {
        this.historialPedidos = historialPedidos;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }
}