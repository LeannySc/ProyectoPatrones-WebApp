package com.marketchasoy.web.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

    private List<Producto> items = new ArrayList<>();

    public void agregarItem(Producto producto) {
        // Lógica simple por ahora, más adelante podríamos manejar cantidades
        this.items.add(producto);
    }

    public void eliminarItem(int productoId) {
        this.items.removeIf(item -> item.getId() == productoId);
    }

    public List<Producto> getItems() {
        return items;
    }

    public int getCantidadItems() {
        return items.size();
    }
    
    public double getTotal() {
        return items.stream().mapToDouble(Producto::getPrecio).sum();
    }
}