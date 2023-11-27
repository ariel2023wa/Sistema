
import com.mysql.cj.x.protobuf.MysqlxCrud;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sistema.entities.Cliente;
import sistema.entities.Servicio;
import sistema.repositories.ClienteRepository;
import sistema.repositories.ServicioRepository;
import sistema.util.Menu;

import java.util.List;
import java.util.Scanner;

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
        Menu.menuPrincipal();

    }
}
