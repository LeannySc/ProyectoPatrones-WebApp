package com.marketchasoy.web.modelo.entidades;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue; // <-- Import añadido
import jakarta.persistence.GenerationType; // <-- Import añadido
import jakarta.persistence.Id;             // <-- Import añadido

@Entity
public class ResultadoPago {

    @Id // <-- 1. Le decimos a JPA cuál es la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // <-- Y que se autogenere
    private Long id;

    private boolean exitoso;
    private String idTransaccion;
    private String mensaje;
    private Date marcaTiempo;

    // 2. ¡Importante! Añadimos el constructor vacío que necesita JPA
    public ResultadoPago() {
    }

    // Tu constructor original está perfecto
    public ResultadoPago(boolean exitoso, String idTransaccion, String mensaje) {
        this.exitoso = exitoso;
        this.idTransaccion = idTransaccion;
        this.mensaje = mensaje;
        this.marcaTiempo = new Date();
    }
    
    // 3. Renombramos los métodos para seguir la convención de getters
    public boolean isExitoso() {
        return exitoso;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public String getMensaje() {
        return mensaje;
    }
    
    // Añadimos el getter para el nuevo campo 'id'
    public Long getId() {
        return id;
    }
    
    public Date getMarcaTiempo() {
        return marcaTiempo;
    }
    

    // Tu método toString está perfecto
    @Override
    public String toString() {
        return "ResultadoPago{" +
                "exitoso=" + exitoso +
                ", idTransaccion='" + idTransaccion + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", marcaTiempo=" + marcaTiempo +
                '}';
    }
}