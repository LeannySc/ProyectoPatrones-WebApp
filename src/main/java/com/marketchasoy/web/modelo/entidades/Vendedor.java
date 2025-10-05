package com.marketchasoy.web.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;


@Entity

public class Vendedor extends Usuario {
    private String nombreTienda;

    @OneToMany(mappedBy = "vendedor")
    private List<Producto> productos = new ArrayList<>();


    
    @Override
    public List<String> obtenerPermisos() {
        return List.of("GESTIONAR_PRODUCTOS", "VER_VENTAS", "GESTIONAR_INVENTARIO");
    }

    @Override
    public String obtenerTablero() {
        return "/dashboard/vendedor";
    }

    @Override
    public String obtenerRol() {
        return "Vendedor";
    }

    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
    }

    public void actualizarProducto(Producto producto) {
        System.out.println("Producto " + producto.getId() + " actualizado.");
    }
}