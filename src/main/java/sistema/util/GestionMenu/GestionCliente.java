package sistema.util.GestionMenu;

import org.checkerframework.checker.units.qual.C;
import sistema.entities.Cliente;
import sistema.entities.Problema;
import sistema.entities.Servicio;
import sistema.service.Controladora;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static sistema.util.UtilidadesGenerales.*;

public class GestionCliente {

    Cliente cliente = new Cliente();

    Controladora controladora = new Controladora();

    public void altaCliente() {

        if (clienteRazonSocial() && clienteCUIT() && clienteServicios()) {

            cliente.setEstado(true);

            controladora.crearCliente(cliente);

            System.out.println("Cliente " + cliente.getRazonSocial() + " creado");

        } else {

            System.out.println("El cliente no fue creado");

        }

    }

    private boolean clienteRazonSocial() {

        boolean flag = false;

        do {

            String opcion = leer("Ingrese la razon social del cliente (Ingrese 0 para cancelar)");

            if (opcion.equals("0")) {

                System.out.println("Cancelando...");

                break;

            }

            if (validar(opcion, "^[a-zA-Z0-9\\s]+$")) {

                cliente.setRazonSocial(opcion);

                flag = true;

                System.out.println("Razon social cargada");

                break;

            } else {

                System.out.println("Razon social invalida");

            }

        } while (true);

        return flag;

    }

    private boolean clienteCUIT() {

        boolean flag = false;

        do {

            String opcion = leer("Ingrese el CUIT del cliente (Ingrese 0 para cancelar)");

            if (opcion.equals("0")) {

                System.out.println("Cancelando...");

                break;

            }

            if (validar(opcion, "\\d{11}")) {

                cliente.setCuit(opcion);

                flag = true;

                System.out.println("CUIT cargado");

                break;

            } else {

                System.out.println("CUIT invalido");

            }

        } while (true);

        return flag;

    }

    private boolean clienteServicios() {

        boolean flag = false;

        Set<Servicio> ServiciosIngresados = new HashSet<>();

        List<Servicio> servicios = controladora.listarServicios();

        List<String> ServiciosNombres = servicios.stream()
                .map(Servicio::getNombre)
                .collect(Collectors.toList());

        ServiciosNombres.add("Terminar");

        do {

            int opcion = imprimirValidarMenu(ServiciosNombres, "Seleccionar servicio");

            if (ServiciosNombres.get(opcion).equals("Terminar")) {

                break;

            } else {

                ServiciosIngresados.add(servicios.get(opcion));

                if (!ServiciosIngresados.isEmpty()) {

                    System.out.println("Servicios Agregados");
                    System.out.println("");

                    ServiciosIngresados.forEach(servicio -> System.out.println(servicio.getNombre()));

                }

            }

        } while (true);

        if (!ServiciosIngresados.isEmpty()) {

            cliente.setServicios(new ArrayList<>(ServiciosIngresados));

            flag = true;

            System.out.println("Servicios cargados");

            cliente.getServicios().forEach(problema -> System.out.println(problema));

        } else {


            System.out.println("No se cargo ningun servicio");

        }

        System.out.println("");
        System.out.println("Terminando...");

        return flag;

    }


    public void bajaCliente() {
    }

    public void modificarCliente() {
    }
}
