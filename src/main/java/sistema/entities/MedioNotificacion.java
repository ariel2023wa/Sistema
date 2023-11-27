package sistema.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "medionotificacion")
public class MedioNotificacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMedioNotificacion;

    @Basic
    private String descripcion;
    private boolean estado;

}

