package pt.historia.prueba.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String nombre;

    @Column(length = 255)
    private String apellido;

    @Column(length = 255)
    private String tipoDocumento;

    @Column(unique = true)
    private Integer documentoIdentificacion;

    @Column(length = 255)
    private String estado;

    private Integer sexo;

    @OneToMany(mappedBy = "usuario")
    private List<Mascota> mascotas;
}
