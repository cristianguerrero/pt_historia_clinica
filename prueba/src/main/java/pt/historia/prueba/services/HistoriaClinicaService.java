package pt.historia.prueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.historia.prueba.dto.HistoriaClinicaDTO;
import pt.historia.prueba.models.HistoriaClinica;
import pt.historia.prueba.repositories.HistoriaClinicaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistoriaClinicaService {
    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

//    public List<HistoriaClinica> getAllHistoriasClinicas() {
//        return historiaClinicaRepository.findAll();
//    }

    public List<HistoriaClinicaDTO> getAllHistoriasClinicas() {
        return historiaClinicaRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private HistoriaClinicaDTO convertToDto(HistoriaClinica historiaClinica) {
        HistoriaClinicaDTO dto = new HistoriaClinicaDTO();
        dto.setId(historiaClinica.getId());
        dto.setFechaCreacion(historiaClinica.getFechaCreacion());
        dto.setIdMascota(historiaClinica.getMascota().getId());

        HistoriaClinicaDTO.MascotaDTO mascotaDto = new HistoriaClinicaDTO.MascotaDTO();
        mascotaDto.setId(historiaClinica.getMascota().getId());
        mascotaDto.setNombre(historiaClinica.getMascota().getNombre());
        mascotaDto.setRaza(historiaClinica.getMascota().getRaza());
        mascotaDto.setSexo(historiaClinica.getMascota().getSexo());

        dto.setMascota(mascotaDto);
        return dto;
    }

    public Optional<HistoriaClinica> getHistoriaClinicaById(Integer id) {
        return historiaClinicaRepository.findById(id);
    }

    public HistoriaClinica createHistoriaClinica(HistoriaClinica historiaClinica) {
        return historiaClinicaRepository.save(historiaClinica);
    }

    public HistoriaClinica updateHistoriaClinica(Integer id, HistoriaClinica historiaClinicaDetails) {
        Optional<HistoriaClinica> optionalHistoriaClinica = historiaClinicaRepository.findById(id);
        if (optionalHistoriaClinica.isPresent()) {
            HistoriaClinica historiaClinica = optionalHistoriaClinica.get();
            historiaClinica.setFechaCreacion(historiaClinicaDetails.getFechaCreacion());
            historiaClinica.setMascota(historiaClinicaDetails.getMascota());
            return historiaClinicaRepository.save(historiaClinica);
        } else {
            throw new RuntimeException("HistoriaClinica not found");
        }
    }

    public void deleteHistoriaClinica(Integer id) {
        historiaClinicaRepository.deleteById(id);
    }
}
