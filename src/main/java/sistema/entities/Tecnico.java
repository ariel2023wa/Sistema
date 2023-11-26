package sistema.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

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

    @ManyToOne
    @JoinColumn(name = "idMedioNotificacion")
    private MedioNotificacion medioNotificacion;

    @Basic
    private String nombre;

    private boolean estado;

}
