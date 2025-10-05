package com.marketchasoy.web.modelo.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// --- Imports Actualizados ---
import com.marketchasoy.web.modelo.entidades.RepositorioUsuario;
import com.marketchasoy.web.modelo.entidades.Usuario;
import com.marketchasoy.web.modelo.patrones.factory.FabricaUsuario;
import com.marketchasoy.web.modelo.patrones.factory.FabricaUsuarioConcreta;
import java.util.Date; // Importa Date si lo usas
import java.util.Map;

@Service
public class ServicioUsuario {

    // 1. Inyectamos el Repositorio. ¡Ya no usamos listas ni contadores!
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    private FabricaUsuario fabricaUsuario = new FabricaUsuarioConcreta();

    public Usuario registrarNuevoUsuario(String rol, Map<String, Object> datos) {
        
        Usuario nuevoUsuario = fabricaUsuario.crearUsuario(rol, datos);

        // 2. Usamos setters para configurar el objeto. ¡Ya no pasamos el ID!
        nuevoUsuario.setNombre((String) datos.get("nombre"));
        nuevoUsuario.setCorreo((String) datos.get("correo"));
        nuevoUsuario.setContrasena((String) datos.get("contrasena"));
        nuevoUsuario.setFechaCreacion(new Date());
        
        // 3. ¡La magia! .save() inserta en la base de datos y le asigna el ID autogenerado.
        System.out.println("Guardando usuario en la base de datos...");
        return repositorioUsuario.save(nuevoUsuario);
    }

    public Usuario buscarUsuarioPorCorreo(String correo) {
        // Este método ahora hace una consulta real a la base de datos.
        return repositorioUsuario.findByCorreo(correo);
    }
     // --- MÉTODO NUEVO PARA LA LÓGICA DE LOGIN ---
    public Usuario autenticarUsuario(String correo, String contrasena) {
        
        // 1. Buscamos al usuario en la base de datos usando el repositorio.
        Usuario usuario = repositorioUsuario.findByCorreo(correo);
        
        // 2. Verificamos si el usuario existe Y si la contraseña coincide.
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            // ¡Las credenciales son correctas!
            return usuario; // Devolvemos el objeto Usuario.
        }
        
        // Si el usuario no existe o la contraseña es incorrecta, devolvemos null.
        return null;
    }
}