package com.marketchasoy.web.modelo.patrones.strategy;

import com.marketchasoy.web.modelo.entidades.ResultadoPago;

public class PagoDaviplata implements EstrategiaPago {
    private String numeroTelefono;

    public PagoDaviplata(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    @Override
    public ResultadoPago procesarPago(float monto) {
        System.out.println("Procesando pago de $" + monto + " con Daviplata al " + numeroTelefono);
        return new ResultadoPago(true, "DVP-54321", "Pago con Daviplata exitoso.");
    }

    @Override
    public boolean validarDatosPago() {
        return numeroTelefono != null && numeroTelefono.length() == 10;
    }

    @Override
    public String obtenerDetallesPago() {
        return "Daviplata (Teléfono: " + numeroTelefono + ")";
    }
}