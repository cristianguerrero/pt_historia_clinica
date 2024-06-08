package pt.historia.prueba.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
public class DetalleHistoriaClinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String temperatura;

    private BigDecimal peso;
    private BigDecimal frecuenciaCardiaca;
    private BigDecimal frecuenciaRespiratoria;
    private Timestamp fechaHora;

    @Column(length = 255)
    private String alimentacion;

    @Column(length = 255)
    private String habitad;

    @Column(length = 255)
    private String observacion;

    @OneToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaborador;

    @ManyToOne
    @JoinColumn(name = "historia_clinica_id", nullable = false)
    private HistoriaClinica historiaClinica;
}
