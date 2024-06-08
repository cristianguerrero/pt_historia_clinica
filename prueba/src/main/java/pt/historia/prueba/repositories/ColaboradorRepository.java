package pt.historia.prueba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.historia.prueba.models.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {
}
