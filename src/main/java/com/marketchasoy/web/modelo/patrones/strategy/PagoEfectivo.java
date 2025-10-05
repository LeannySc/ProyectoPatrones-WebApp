package com.marketchasoy.web.modelo.patrones.strategy;

import com.marketchasoy.web.modelo.entidades.ResultadoPago;

public class PagoEfectivo implements EstrategiaPago {
    private String direccionEntrega;

    public PagoEfectivo(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    @Override
    public ResultadoPago procesarPago(float monto) {
        System.out.println("Procesando pago en EFECTIVO por $" + monto + " a la dirección " + direccionEntrega);
        if (validarDatosPago()) {
            return new ResultadoPago(true, "EFC-11223", "Pago contra entrega solicitado.");
        }
        return new ResultadoPago(false, null, "Dirección de entrega no válida.");
    }

    @Override
    public boolean validarDatosPago() {
        return this.direccionEntrega != null && !this.direccionEntrega.isEmpty();
    }

    @Override
    public String obtenerDetallesPago() {
        return "Pago en Efectivo (Contra-entrega)";
    }
}