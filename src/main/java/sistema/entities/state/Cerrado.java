package sistema.entities.state;

import jakarta.persistence.*;
import sistema.entities.Incidente;
import sistema.entities.state.Abierto;
import sistema.entities.state.Estado;

@Entity
@DiscriminatorValue("Cerrado")
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
