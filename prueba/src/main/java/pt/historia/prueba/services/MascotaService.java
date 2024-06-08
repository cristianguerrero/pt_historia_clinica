package pt.historia.prueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.historia.prueba.dto.HistoriaClinicaDTO;
import pt.historia.prueba.dto.MascotaDTO;
import pt.historia.prueba.models.HistoriaClinica;
import pt.historia.prueba.models.Mascota;
import pt.historia.prueba.repositories.MascotaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MascotaService {
    @Autowired
    private MascotaRepository mascotaRepository;

//    public List<Mascota> getAllMascotas() {
//        return mascotaRepository.findAll();
//    }

    public List<MascotaDTO> getAllMascotas() {
        return mascotaRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private MascotaDTO convertToDto(Mascota mascota) {
        MascotaDTO dto = new MascotaDTO();
        dto.setId(mascota.getId());
        dto.setNombre(mascota.getNombre());
        dto.setRaza(mascota.getRaza());
        dto.setSexo(mascota.getSexo());

        MascotaDTO.UsuarioDTO usuarioDto = new MascotaDTO.UsuarioDTO();
        usuarioDto.setId(mascota.getUsuario().getId());
        usuarioDto.setNombre(mascota.getUsuario().getNombre());
        usuarioDto.setApellido(mascota.getUsuario().getApellido());
        usuarioDto.setDocumentoIdentificacion(mascota.getUsuario().getDocumentoIdentificacion());
        usuarioDto.setEstado(mascota.getUsuario().getEstado());

        dto.setUsuario(usuarioDto);
        return dto;
    }

    public Mascota crearMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }
}
