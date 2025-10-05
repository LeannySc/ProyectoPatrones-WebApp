package com.marketchasoy.web.modelo.entidades;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository


public interface RepositorioUsuario extends JpaRepository<Usuario, Integer> {

    // Con solo nombrar este método, Spring Data JPA sabe cómo implementarlo:
    // "SELECT * FROM usuario WHERE correo = ?"
    Usuario findByCorreo(String correo);

}