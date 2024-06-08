package pt.historia.prueba.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String nombre;

    @Column(length = 255)
    private String apellido;

    @Column(length = 255)
    private String cargo;

    @Column(length = 255)
    private String especialidad;

    @Column(length = 3)
    private String tipoDocumento;

    private Integer documentoIdentificacion;

    @OneToOne(mappedBy = "colaborador")
    private DetalleHistoriaClinica detallesHistoriasClinicas;

}
