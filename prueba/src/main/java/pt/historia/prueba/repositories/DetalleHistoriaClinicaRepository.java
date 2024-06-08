package pt.historia.prueba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.historia.prueba.models.DetalleHistoriaClinica;

public interface DetalleHistoriaClinicaRepository extends JpaRepository<DetalleHistoriaClinica, Integer> {
}
