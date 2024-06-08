package pt.historia.prueba.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.historia.prueba.dto.HistoriaClinicaDTO;
import pt.historia.prueba.models.HistoriaClinica;
import pt.historia.prueba.models.Mascota;
import pt.historia.prueba.repositories.MascotaRepository;
import pt.historia.prueba.services.HistoriaClinicaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/historias-clinicas")
public class HistoriaClinicaController {
    @Autowired
    private HistoriaClinicaService historiaClinicaService;

    @Autowired
    private MascotaRepository mascotaRepository;

    @GetMapping
    public List<HistoriaClinicaDTO> getAllHistoriasClinicas() {
        return historiaClinicaService.getAllHistoriasClinicas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoriaClinica> getHistoriaClinicaById(@PathVariable Integer id) {
        return historiaClinicaService.getHistoriaClinicaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Object> createHistoriaClinica(@RequestBody HistoriaClinica historiaClinica) {
        Optional<Mascota> optionalMascota = mascotaRepository.findById(historiaClinica.getMascota().getId());
        if (optionalMascota.isPresent()) {
            historiaClinica.setMascota(optionalMascota.get());
            HistoriaClinica createdHistoriaClinica = historiaClinicaService.createHistoriaClinica(historiaClinica);
            //return ResponseEntity.ok(createdHistoriaClinica);
            return ResponseEntity.ok().body(new HistoriaResponse(createdHistoriaClinica.getId(), createdHistoriaClinica.getFechaCreacion()));
        } else {
            return ResponseEntity.badRequest().body(null);  // Mascota no encontrada
        }
    }

    // Clase para representar la respuesta simplificada
    @Setter
    @Getter
    private static class HistoriaResponse {
        private Integer id;
        private Integer fechaCreacion;

        public HistoriaResponse(Integer id, Integer fechaCreacion) {
            this.id = id;
            this.fechaCreacion = fechaCreacion;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoriaClinica> updateHistoriaClinica(@PathVariable Integer id, @RequestBody HistoriaClinica historiaClinicaDetails) {
        return ResponseEntity.ok(historiaClinicaService.updateHistoriaClinica(id, historiaClinicaDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistoriaClinica(@PathVariable Integer id) {
        historiaClinicaService.deleteHistoriaClinica(id);
        return ResponseEntity.noContent().build();
    }
}
