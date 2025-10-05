package com.marketchasoy.web.modelo.patrones.strategy;

import com.marketchasoy.web.modelo.entidades.Pedido;
import com.marketchasoy.web.modelo.entidades.ResultadoPago;

import java.util.List;

public class ContextoPago {
    private EstrategiaPago estrategia;
    private Pedido pedido;

    public ContextoPago(Pedido pedido) {
        this.pedido = pedido;
    }

    public void establecerEstrategia(EstrategiaPago estrategia) {
        this.estrategia = estrategia;
        System.out.println("Estrategia de pago establecida a: " + estrategia.obtenerDetallesPago());
    }

    public List<String> obtenerMetodosPagoDisponibles() {
        return List.of("Nequi", "PSE", "Daviplata", "Efectivo");
    }

    public void seleccionarMetodoPago(String metodo) {
        // En una aplicación real, los datos se pedirían al usuario en este punto.
        EstrategiaPago nuevaEstrategia = switch (metodo.toLowerCase()) {
            case "nequi" -> new PagoNequi("3119876543"); // Dato de ejemplo
            case "pse" -> new PagoPSE("1022-Davivienda"); // Dato de ejemplo
            case "daviplata" -> new PagoDaviplata("3201234567"); // Dato de ejemplo
            case "efectivo" -> new PagoEfectivo("Calle 123 #45-67"); // Dato de ejemplo
            default -> throw new IllegalArgumentException("Método de pago no soportado: " + metodo);
        };
        this.establecerEstrategia(nuevaEstrategia);
    }

    public ResultadoPago ejecutarPago() {
        if (estrategia == null) {
            throw new IllegalStateException("Debe seleccionar un método de pago primero.");
        }
        float monto = pedido.getMontoTotal();
        return estrategia.procesarPago(monto);
    }
}