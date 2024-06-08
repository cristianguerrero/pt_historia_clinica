package pt.historia.prueba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.historia.prueba.models.Mascota;

public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
}
