package sistema.entities.state;

import jakarta.persistence.*;
import sistema.entities.Incidente;

@Entity
public class Abierto extends Estado {
    public Abierto() {
        super.setNombre("Abierto");
    }

    @Override
    public void cerrar(Incidente incidente) {
        Estado cerrado = new Cerrado();
        incidente.setEstado(cerrado);
    }
}
