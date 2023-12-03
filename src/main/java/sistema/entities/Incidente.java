package sistema.entities;

import jakarta.persistence.*;
import lombok.Data;
import sistema.entities.state.Estado;

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

    @ManyToOne()
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

    @Override
    public String toString() {
        StringBuilder problemasStr = new StringBuilder("[");
        problemas.forEach(problema -> problemasStr.append(problema.getTipoProblema()).append(", "));
        if (problemasStr.length() > 1) {
            problemasStr.delete(problemasStr.length() - 2, problemasStr.length());  // Elimina la última coma y espacio
        }
        problemasStr.append("]");

        return "Incidente{" +
                "idIncidente=" + idIncidente +
                ", cliente=" + cliente.getRazonSocial() +
                ", servicio=" + servicio.getNombre() +
                ", tecnico=" + tecnico.getNombre() +
                ", problemas=" + problemasStr.toString() +
                ", descripcion='" + descripcion + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", fechaPosResolucion=" + fechaPosResolucion +
                ", horasEstimadas=" + horasEstimadas +
                ", estado=" + estado +
                '}';
    }

}