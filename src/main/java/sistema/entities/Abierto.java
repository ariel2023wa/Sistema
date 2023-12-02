package sistema.entities;

import jakarta.persistence.*;

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
