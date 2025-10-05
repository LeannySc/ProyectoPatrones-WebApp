package com.marketchasoy.web.modelo.entidades;

// Imports de JPA necesarios
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private double precio;
    private String categoria;
    private String descripcion;
    private String urlImagen;
    
    @ManyToOne
    private Vendedor vendedor;

    public Producto() {}

    // Este constructor está bien, no lo tocamos
    public Producto(int id, String nombre, double precio, String categoria, String descripcion, String urlImagen) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
    }

    // --- GETTERS CON LA CONVENCIÓN ESTÁNDAR (CORREGIDOS) ---
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    // 3. Añade su getter
    public String getUrlImagen() {
    return urlImagen;
    }
}