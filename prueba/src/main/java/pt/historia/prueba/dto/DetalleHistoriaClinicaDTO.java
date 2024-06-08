package pt.historia.prueba.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter
public class DetalleHistoriaClinicaDTO {
    private Integer id;
    private String temperatura;
    private BigDecimal peso;
    private BigDecimal frecuenciaCardiaca;
    private BigDecimal frecuenciaRespiratoria;
    private Timestamp fechaHora;
    private String alimentacion;
    private String habitad;
    private String observacion;
    private Integer colaboradorId;
    private Integer historiaClinicaId;
    private HistoriaClinicaDTO historiaClinica;

    @Setter
    @Getter
    public static class HistoriaClinicaDTO {
        private Integer id;
        private Integer fechaCreacion;
    }
}
