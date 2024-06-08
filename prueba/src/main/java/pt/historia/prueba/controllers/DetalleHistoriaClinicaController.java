package pt.historia.prueba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.historia.prueba.dto.DetalleHistoriaClinicaDTO;
import pt.historia.prueba.models.DetalleHistoriaClinica;
import pt.historia.prueba.services.DetalleHistoriaClinicaService;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-historia-clinica")
public class DetalleHistoriaClinicaController {
    @Autowired
    private DetalleHistoriaClinicaService detalleHistoriaClinicaService;

    @GetMapping
    public List<DetalleHistoriaClinicaDTO> getAllDetallesHistoriaClinica() {
        return detalleHistoriaClinicaService.getAllDetallesHistoriaClinica();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleHistoriaClinica> getDetalleHistoriaClinicaById(@PathVariable Integer id) {
        return detalleHistoriaClinicaService.getDetalleHistoriaClinicaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetalleHistoriaClinica createDetalleHistoriaClinica(@RequestBody DetalleHistoriaClinica detalleHistoriaClinica) {
        return detalleHistoriaClinicaService.createDetalleHistoriaClinica(detalleHistoriaClinica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleHistoriaClinica> updateDetalleHistoriaClinica(@PathVariable Integer id, @RequestBody DetalleHistoriaClinica detalleHistoriaClinicaDetails) {
        return ResponseEntity.ok(detalleHistoriaClinicaService.updateDetalleHistoriaClinica(id, detalleHistoriaClinicaDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleHistoriaClinica(@PathVariable Integer id) {
        detalleHistoriaClinicaService.deleteDetalleHistoriaClinica(id);
        return ResponseEntity.noContent().build();
    }
}
