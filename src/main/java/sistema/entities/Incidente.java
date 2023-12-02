package sistema.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "incidente")
public class Incidente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idIncidente;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idServicio")
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "idTecnico")
    private Tecnico tecnico;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "incidenteproblema",
            joinColumns = @JoinColumn(name = "idIncidente"),
            inverseJoinColumns = @JoinColumn(name = "idProblema")
    )
    private List<Problema> problemas;

    private String descripcion;

    private LocalDate fechaIngreso;

    private LocalDate fechaPosResolucion;

    private int horasEstimadas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estado")
    private Estado estado;

}
