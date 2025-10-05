package com.marketchasoy.web.modelo.patrones.strategy;

import com.marketchasoy.web.modelo.entidades.ResultadoPago;

public class PagoStripe implements EstrategiaPago {
    
    // Podemos tener atributos para los detalles específicos del pago con tarjeta
    private String tokenTarjeta; // Representa los datos de la tarjeta de forma segura

    public PagoStripe(String tokenTarjeta) {
        this.tokenTarjeta = tokenTarjeta;
    }

    // --- MÉTODO CORREGIDO ---
    // Ahora coincide exactamente con la firma de la interfaz.
    @Override
    public ResultadoPago procesarPago(float monto) {
        System.out.println("Procesando pago con Stripe...");
        System.out.println("Monto: " + monto);
        System.out.println("Usando token de tarjeta que termina en: ... " + 
                           (tokenTarjeta != null && tokenTarjeta.length() > 4 ? 
                           tokenTarjeta.substring(tokenTarjeta.length() - 4) : ""));

        // AQUÍ irá la lógica real para llamar a la API de Stripe
        // pasándole el 'monto' y el 'tokenTarjeta'.
        
        // Simulación
        return new ResultadoPago(true, "stripe_trans_" + System.currentTimeMillis(), "Pago con Stripe exitoso (simulado).");
    }
    
    // Implementaciones simples para los otros métodos, si tu interfaz los tiene
    @Override
    public boolean validarDatosPago() {
        return tokenTarjeta != null && !tokenTarjeta.isEmpty();
    }

    @Override
    public String obtenerDetallesPago() {
        return "Pago procesado con Stripe.";
    }

    @Override
    public boolean reembolsar(String idTransaccion) {
        System.out.println("Reembolsando transacción de Stripe: " + idTransaccion);
        return true;
    }
}