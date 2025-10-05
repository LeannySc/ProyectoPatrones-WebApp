package com.marketchasoy.web.modelo.patrones.strategy;

import com.marketchasoy.web.modelo.entidades.ResultadoPago;

public interface EstrategiaPago {
    ResultadoPago procesarPago(float monto);

    boolean validarDatosPago();

    String obtenerDetallesPago();

    default boolean reembolsar(String idTransaccion) {
        System.out.println("Procesando reembolso para la transacción: " + idTransaccion);
        return true; // Simulación
    }
}