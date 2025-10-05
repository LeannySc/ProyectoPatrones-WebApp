package com.marketchasoy.web.modelo.patrones.strategy;

import com.marketchasoy.web.modelo.entidades.ResultadoPago;;

public class PagoNequi implements EstrategiaPago {
    private String numeroTelefono;

    public PagoNequi(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    @Override
    public ResultadoPago procesarPago(float monto) {
        System.out.println("Procesando pago de $" + monto + " con Nequi al " + numeroTelefono);
        if (validarDatosPago()) {
            return new ResultadoPago(true, "NEQUI-12345", "Pago exitoso");
        }
        return new ResultadoPago(false, null, "Número de teléfono inválido.");
    }

    @Override
    public boolean validarDatosPago() {
        return numeroTelefono != null && numeroTelefono.length() == 10;
    }

    @Override
    public String obtenerDetallesPago() {
        return "Nequi (Teléfono: " + numeroTelefono + ")";
    }
}