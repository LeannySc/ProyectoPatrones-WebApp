package com.marketchasoy.web.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketchasoy.web.modelo.entidades.Carrito;
import com.marketchasoy.web.modelo.entidades.Producto;
import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorProductoWeb {

    private List<Producto> listaDeProductosGlobal;

    public ControladorProductoWeb() {
        listaDeProductosGlobal = new ArrayList<>();
        listaDeProductosGlobal.add(new Producto(1, "Camiseta Cool", 25000.00, "Ropa", "Una camiseta con estilo.", "/images/products/camiseta-cool.jpg"));
        listaDeProductosGlobal.add(new Producto(2, "Jean Clásico", 8000.00, "Ropa", "Un pantalón que no pasa de moda.", "/images/products/jean-clasico.jpg"));
        listaDeProductosGlobal.add(new Producto(3, "Zapatos Deportivos", 150000.00, "Calzado", "Zapatos para correr.", "/images/products/zapatos-deportivos.jpg"));
    }

    @GetMapping("/productos")
    public String mostrarCatalogo(Model model) {
        model.addAttribute("listaDeProductos", listaDeProductosGlobal);
        return "catalogo-productos";
    }

    @GetMapping("/producto/{id}")
    public String mostrarDetalleProducto(@PathVariable int id, Model model) {
        Producto productoEncontrado = listaDeProductosGlobal.stream()
            .filter(p -> p.getId() == id)
            .findFirst()
            .orElse(null);

        if (productoEncontrado != null) {
            model.addAttribute("producto", productoEncontrado);
            return "detalle-producto";
        }
        return "redirect:/productos";
    }

    @PostMapping("/carrito/agregar")
    public String agregarAlCarrito(@RequestParam("productoId") int productoId, HttpSession session) {
        Producto productoParaAnadir = listaDeProductosGlobal.stream()
            .filter(p -> p.getId() == productoId)
            .findFirst()
            .orElse(null);
        
        if (productoParaAnadir != null) {
            Carrito carrito = (Carrito) session.getAttribute("carrito");
            if (carrito == null) {
                carrito = new Carrito();
            }
            carrito.agregarItem(productoParaAnadir);
            session.setAttribute("carrito", carrito);
        }
        return "redirect:/producto/" + productoId;
    }
}