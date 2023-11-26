package sistema.service;

import sistema.entities.Cliente;
import sistema.repositories.ControladoraPersistencia;

public class Controladora {

    ControladoraPersistencia contP = new ControladoraPersistencia();

    public void crearCliente(Cliente cliente) {

        contP.crearCliente(cliente);

    }

    public Cliente buscarClienteCUIT(String cuit) {

        return contP.buscarClienteCUIT(cuit);

    }

}
