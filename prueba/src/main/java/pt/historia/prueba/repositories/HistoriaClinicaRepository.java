package pt.historia.prueba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.historia.prueba.models.HistoriaClinica;

public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Integer> {
}
