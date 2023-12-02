package sistema.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "tecnico")
public class Tecnico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTecnico;

    @ManyToOne
    @JoinColumn(name = "idEspecialidad")
    private Especialidad especialidad;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tecnico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incidente> incidentes;

    @ManyToOne
    @JoinColumn(name = "idMedioNotificacion")
    private MedioNotificacion medioNotificacion;

    @Basic
    private String nombre;

    private String nroLegajo;

}
