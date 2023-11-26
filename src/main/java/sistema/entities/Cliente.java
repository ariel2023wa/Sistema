package sistema.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "clienteservicio",
            joinColumns = @JoinColumn(name = "idCliente"),
            inverseJoinColumns = @JoinColumn(name = "idServicio")
    )
    private List<Servicio> servicios;

    @Basic
    private String razonSocial;

    private String cuit;

    private boolean estado;

}
