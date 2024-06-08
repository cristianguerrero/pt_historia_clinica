package pt.historia.prueba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.historia.prueba.dto.MascotaDTO;
import pt.historia.prueba.models.Mascota;
import pt.historia.prueba.services.MascotaService;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {
    @Autowired
    private MascotaService mascotaService;

    @GetMapping
    public List<MascotaDTO> getAllMascotas() {
        return mascotaService.getAllMascotas();
    }

    @PostMapping
    public Mascota crearMascota(@RequestBody Mascota mascota) {
        return mascotaService.crearMascota(mascota);
    }

}
