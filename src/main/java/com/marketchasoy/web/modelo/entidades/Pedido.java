package com.marketchasoy.web.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne; // Import necesario
// ¡Ya no necesitas ElementCollection ni CollectionTable!

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float montoTotal;
    private String estado;
    
    // --- LA OTRA MITAD DE LA RELACIÓN ---
    // MUCHOS Pedidos pertenecen a UN Comprador
    @ManyToOne
    private Comprador comprador;

    // Nota: El 'List<String> elementos' puede necesitar su propia tabla de relación si
    // no quieres usar @ElementCollection, pero por ahora podemos comentarlo para arrancar.
    // private List<String> elementos = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(float montoTotal) {
        this.montoTotal = montoTotal;
        this.estado = "PENDIENTE";
    }
    
    // --- Getters y Setters para TODOS los campos ---
    public int getId() {
        return id;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }
}