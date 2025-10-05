package com.marketchasoy.web.modelo.patrones.singleton;

// Simulación de una conexión a base de datos.
class Conexion {
    public void conectar(String servidor, int puerto, String baseDatos) {
        System.out.println("Conectando a " + baseDatos + " en " + servidor + ":" + puerto);
    }
    public void desconectar() {
        System.out.println("Desconectado de la base de datos.");
    }
    public String ejecutarConsulta(String consulta) {
        return "Resultados de la consulta: " + consulta;
    }
}

public class ConexionBaseDatos {
    // La única instancia de la clase (Singleton)
    private static ConexionBaseDatos instancia;
    private Conexion conexion;

    // Propiedades de la conexión
    private String servidor = "localhost";
    private int puerto = 5432;
    private String baseDatos = "ecommerce_db";

    /**
     * El constructor es privado para evitar instanciación directa.
     * Solo existe una conexión a la base de datos.
     */
    private ConexionBaseDatos() {
        this.conexion = new Conexion();
    }

    /**
     * Método público y estático para obtener la instancia única.
     */
    public static synchronized ConexionBaseDatos obtenerInstancia() {
        if (instancia == null) {
            instancia = new ConexionBaseDatos();
        }
        return instancia;
    }

    public void conectar() {
        conexion.conectar(servidor, puerto, baseDatos);
    }

    public void desconectar() {
        conexion.desconectar();
    }

    public String ejecutarConsulta(String consulta) {
        return conexion.ejecutarConsulta(consulta);
    }

    public int ejecutarActualizacion(String consulta) {
        System.out.println("Ejecutando actualización: " + consulta);
        return 1; // Retorna 1 fila afectada (simulación)
    }
}