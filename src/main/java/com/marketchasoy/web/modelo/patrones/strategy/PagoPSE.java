package com.marketchasoy.web.modelo.patrones.strategy;

import com.marketchasoy.web.modelo.entidades.ResultadoPago;

public class PagoPSE implements EstrategiaPago {
    private String codigoBanco;

    public PagoPSE(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    @Override
    public ResultadoPago procesarPago(float monto) {
        System.out.println("Procesando pago de $" + monto + " con PSE del banco " + codigoBanco);
        return new ResultadoPago(true, "PSE-67890", "Pago con PSE redirigido correctamente.");
    }

    @Override
    public boolean validarDatosPago() {
        return codigoBanco != null && !codigoBanco.isEmpty();
    }

    @Override
    public String obtenerDetallesPago() {
        return "PSE (Banco: " + codigoBanco + ")";
    }
}