package com.marketchasoy.web.modelo.patrones.factory;

import java.util.Date;
import java.util.Map;

import com.marketchasoy.web.modelo.entidades.Administrador;
import com.marketchasoy.web.modelo.entidades.Comprador;
import com.marketchasoy.web.modelo.entidades.Usuario;
import com.marketchasoy.web.modelo.entidades.Vendedor;

public class FabricaUsuarioConcreta extends FabricaUsuario {
    /**
     * La fábrica decide qué tipo de usuario crear basándose en el parámetro 'tipo'
     */
    @Override
    public Usuario crearUsuario(String tipo, Map<String, Object> datos) {
        Usuario usuario = switch (tipo.toLowerCase()) {
            case "comprador" -> new Comprador();
            case "vendedor" -> new Vendedor();
            case "administrador" -> new Administrador();
            default -> throw new IllegalArgumentException("Tipo de usuario desconocido: " + tipo);
        };

        
        return usuario;
    }
}