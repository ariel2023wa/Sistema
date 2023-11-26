package sistema.repositories;

import sistema.entities.Cliente;

public class ControladoraPersistencia {

    ClienteRepository cr = new ClienteRepository();

    public void crearCliente(Cliente cliente){

        cr.create(cliente);

    }

    public Cliente buscarClienteCUIT(String cuit) {

       return cr.findCUITCliente(cuit);

    }



}
