package pt.historia.prueba.dto;

import lombok.*;

@Setter
@Getter
public class HistoriaClinicaDTO {
    private Integer id;
    private Integer fechaCreacion;
    private Integer idMascota;
    private MascotaDTO mascota;

    @Setter
    @Getter
    public static class MascotaDTO {
        private Integer id;
        private String nombre;
        private String raza;
        private String sexo;

    }
}
