package pt.historia.prueba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.historia.prueba.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
