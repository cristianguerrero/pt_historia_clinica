package pt.historia.prueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.historia.prueba.dto.DetalleHistoriaClinicaDTO;
import pt.historia.prueba.models.DetalleHistoriaClinica;
import pt.historia.prueba.repositories.DetalleHistoriaClinicaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DetalleHistoriaClinicaService {
    @Autowired
    private DetalleHistoriaClinicaRepository detalleHistoriaClinicaRepository;

//    public List<DetalleHistoriaClinica> getAllDetallesHistoriaClinica() {
//        return detalleHistoriaClinicaRepository.findAll();
//    }

    public List<DetalleHistoriaClinicaDTO> getAllDetallesHistoriaClinica() {
        return detalleHistoriaClinicaRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private DetalleHistoriaClinicaDTO convertToDto(DetalleHistoriaClinica detalle) {
        DetalleHistoriaClinicaDTO dto = new DetalleHistoriaClinicaDTO();
        dto.setId(detalle.getId());
        dto.setTemperatura(detalle.getTemperatura());
        dto.setPeso(detalle.getPeso());
        dto.setFrecuenciaCardiaca(detalle.getFrecuenciaCardiaca());
        dto.setFrecuenciaRespiratoria(detalle.getFrecuenciaRespiratoria());
        dto.setFechaHora(detalle.getFechaHora());
        dto.setAlimentacion(detalle.getAlimentacion());
        dto.setHabitad(detalle.getHabitad());
        dto.setObservacion(detalle.getObservacion());
        dto.setColaboradorId(detalle.getColaborador().getId());
        dto.setHistoriaClinicaId(detalle.getHistoriaClinica().getId());

        DetalleHistoriaClinicaDTO.HistoriaClinicaDTO historiaClinicaDto = new DetalleHistoriaClinicaDTO.HistoriaClinicaDTO();
        historiaClinicaDto.setId(detalle.getHistoriaClinica().getId());
        historiaClinicaDto.setFechaCreacion(detalle.getHistoriaClinica().getFechaCreacion());

        dto.setHistoriaClinica(historiaClinicaDto);
        return dto;
    }

    public Optional<DetalleHistoriaClinica> getDetalleHistoriaClinicaById(Integer id) {
        return detalleHistoriaClinicaRepository.findById(id);
    }

    public DetalleHistoriaClinica createDetalleHistoriaClinica(DetalleHistoriaClinica detalleHistoriaClinica) {
        return detalleHistoriaClinicaRepository.save(detalleHistoriaClinica);
    }

    public DetalleHistoriaClinica updateDetalleHistoriaClinica(Integer id, DetalleHistoriaClinica detalleHistoriaClinicaDetails) {
        Optional<DetalleHistoriaClinica> optionalDetalleHistoriaClinica = detalleHistoriaClinicaRepository.findById(id);
        if (optionalDetalleHistoriaClinica.isPresent()) {
            DetalleHistoriaClinica detalleHistoriaClinica = optionalDetalleHistoriaClinica.get();
            detalleHistoriaClinica.setTemperatura(detalleHistoriaClinicaDetails.getTemperatura());
            detalleHistoriaClinica.setPeso(detalleHistoriaClinicaDetails.getPeso());
            detalleHistoriaClinica.setFrecuenciaCardiaca(detalleHistoriaClinicaDetails.getFrecuenciaCardiaca());
            detalleHistoriaClinica.setFrecuenciaRespiratoria(detalleHistoriaClinicaDetails.getFrecuenciaRespiratoria());
            detalleHistoriaClinica.setFechaHora(detalleHistoriaClinicaDetails.getFechaHora());
            detalleHistoriaClinica.setAlimentacion(detalleHistoriaClinicaDetails.getAlimentacion());
            detalleHistoriaClinica.setHabitad(detalleHistoriaClinicaDetails.getHabitad());
            detalleHistoriaClinica.setObservacion(detalleHistoriaClinicaDetails.getObservacion());
            detalleHistoriaClinica.setColaborador(detalleHistoriaClinicaDetails.getColaborador());
            detalleHistoriaClinica.setHistoriaClinica(detalleHistoriaClinicaDetails.getHistoriaClinica());
            return detalleHistoriaClinicaRepository.save(detalleHistoriaClinica);
        } else {
            throw new RuntimeException("DetalleHistoriaClinica not found");
        }
    }

    public void deleteDetalleHistoriaClinica(Integer id) {
        detalleHistoriaClinicaRepository.deleteById(id);
    }
}
