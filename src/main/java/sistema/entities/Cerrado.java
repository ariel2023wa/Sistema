package sistema.entities;

import jakarta.persistence.*;

@Entity
public class Cerrado extends Estado {

    public Cerrado() {
        super.setNombre("Cerrado");
    }

    @Override
    public void abrir(Incidente incidente) {
        Estado abierto = new Abierto();
        incidente.setEstado(abierto);
    }

}
