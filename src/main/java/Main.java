
import sistema.GestionMenu.Menu;
import sistema.entities.Abierto;
import sistema.entities.Estado;
import sistema.entities.Incidente;
import sistema.repositories.IncidenteRepository;

public class Main {

    public static void main(String[] args) {
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
      /*  Menu mm = new Menu();

        String menu = "Menu Principal";

        do {
            menu = (mm.generarMenu(menu));
        }while(true);*/

        Incidente incidente = new Incidente();

        incidente.setDescripcion("aaaaa");
        incidente.setEstado(new Abierto());
        incidente.getEstado().abrir(incidente);

        IncidenteRepository ir = new IncidenteRepository();

        //ir.create(incidente);

        //System.out.println(incidente+"creado");

        Incidente incidente2 = new Incidente();

        incidente2 = ir.findIncidente(4);

        incidente2.getEstado().cerrar(incidente2);

        System.out.println(incidente2);

    }
}
