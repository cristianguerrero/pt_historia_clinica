package pt.historia.prueba.dto;

import lombok.*;

@Setter
@Getter
public class MascotaDTO {
    private Integer id;
    private String nombre;
    private String raza;
    private String sexo;
    private UsuarioDTO usuario;

    @Setter
    @Getter
    public static class UsuarioDTO {
        private Integer id;
        private String nombre;
        private String apellido;
        private Integer documentoIdentificacion;
        private String estado;

    }
}
