package sistema.entities;


import jakarta.persistence.*;

@Entity
@Inheritance
@DiscriminatorColumn(name = "tipo_estado")
@Table(name = "estados")
public abstract class Estado {
    @Id
    protected String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String descripcion) {
        this.nombre = descripcion;
    }

    public void abrir(Incidente incidente){

    }
    public void cerrar(Incidente incidente){

    }

    @Override
    public String toString() {
        return "Estado{" +
                "descripcion='" + nombre + '\'' +
                '}';
    }

}
