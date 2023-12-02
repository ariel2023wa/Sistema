
import sistema.entities.Abierto;
import sistema.entities.Incidente;
import sistema.repositories.IncidenteRepository;
import sistema.repositories.exceptions.NonexistentEntityException;
import sistema.util.GestionMenu.Menu;

public class Main {

    public static void main(String[] args) throws NonexistentEntityException {
/*
        ServicioRepository sr = new ServicioRepository();

        Servicio servicio = new Servicio();
        servicio.setNombre("Ubuntu");
        servicio.setEstado(true);

        //sr.create(servicio);

        ClienteRepository cr = new ClienteRepository();

        Cliente cliente = new Cliente();
        cliente.setRazonSocial("Roberto");
        cliente.setEstado(true);
        List list = sr.findServicioEntities();
        cliente.setServicios(list);

        cliente.getServicios().forEach(System.out::println);

        cr.create(cliente);
*/
        Menu mm = new Menu();

        mm.generarMenu();

    }
}
