package sistema.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "problema")
public class Problema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProblema;

    @Basic
    private String tipoProblema;
    private int horasEstimadas;
    private boolean estado;

}
