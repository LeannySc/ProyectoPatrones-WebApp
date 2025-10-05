package com.marketchasoy.web.modelo.patrones.factory;

import java.util.Map;

import com.marketchasoy.web.modelo.entidades.Usuario;

public abstract class FabricaUsuario {
    public abstract Usuario crearUsuario(String tipo, Map<String, Object> datos);
}