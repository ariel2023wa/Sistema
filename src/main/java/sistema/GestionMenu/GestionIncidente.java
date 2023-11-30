package sistema.GestionMenu;

import sistema.entities.Cliente;
import sistema.entities.Incidente;
import sistema.entities.Problema;
import sistema.entities.Servicio;
import sistema.service.Controladora;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static sistema.util.MetodosUtiles.*;

public class GestionIncidente {

    private Incidente incidente = new Incidente();

    private Controladora contr = new Controladora();

    private boolean clienteNull() {
        return incidente.getCliente() == null;
    }

    private boolean servicioNull() {
        return incidente.getServicio() == null;
    }

    private boolean descNull() {
        return incidente.getDescripcion() == null;
    }

    private boolean problemaNull() {
        return incidente.getProblemas() == null;
    }

    private boolean tecnicoNull() {
        return incidente.getTecnico() == null;
    }

    private boolean fechaNull() {
        return incidente.getFechaIngreso() == null;
    }

    public void chequearCliente() {

        if (clienteNull()) {

            buscarCliente();

        } else {

            System.out.println("El cliente ya esta cargado");

        }

    }

    private void buscarCliente() {

        String opcion;

        do {

            opcion = leer("Ingrese el CUIT sin guiones (Ingrese 0 para cancelar): ");

            if (opcion.equals("0")) {
                break;
            }

            if (validar(opcion, "\\d{11}")) {

                Cliente cliente = contr.buscarClienteCUIT(opcion);

                if (cliente != null) {

                    incidente.setCliente(cliente);

                    System.out.println("Cliente " + cliente.getRazonSocial() + " encontrado.");

                    break;

                } else {

                    System.out.println("No se encontro el cliente");
                    System.out.println("");

                }

            } else {

                System.out.println("CUIT incorrecto");

            }

        } while (true);

    }

    public void chequearServicio() {

        if (clienteNull()) {

            System.out.println("Debe cargar un cliente primero");

        } else if (!servicioNull()) {

            System.out.println("El servicio ya se cargo");

        } else {

            seleccionarServicio();

        }

    }

    public void seleccionarServicio() {

        String opcion;

        List<Servicio> servicios = incidente.getCliente().getServicios();

        List<String> serviciosNombres = servicios.stream()
                .map(Servicio::getNombre)
                .collect(Collectors.toList());

        opcion = imprimirValidarMenu(serviciosNombres, "Seleccionar servicio");

        incidente.setServicio(servicios.get(Integer.parseInt(opcion) - 1));

        System.out.println("Servicio Agregado");

    }

    public void chequearDesc() {

        if (descNull()) {

            ingresarDescripcion();

        } else {

            System.out.println("La descripcion ya fue ingresada");

        }

    }

    private void ingresarDescripcion() {

        String opcion;

        do {

            opcion = leer("Ingrese una descripcion del incidente");

            if (validar(opcion, "^(?!\\s*$).+")) {

                incidente.setDescripcion(opcion);

                System.out.println("Descripcion cargada");

                break;

            } else {

                System.out.println("Ingreso una descripcion valida");

            }

        } while (true);

    }

    public void chequearProblema() {

        if (problemaNull()) {

            seleccionarProblema();

        } else {

            System.out.println("El problema ya fue ingresado");

        }

    }

    private void seleccionarProblema() {


        String opcion;

        incidente.setProblemas(new ArrayList<>());

        List<Problema> problemas = contr.listarProblemas();

        List<String> problemasNombres = problemas.stream()
                .map(Problema::getTipoProblema)
                .collect(Collectors.toList());

        do {

            opcion = imprimirValidarMenu(problemasNombres, "Seleccionar problema");

            if (opcion.equalsIgnoreCase("s")){
                break;
            }

            incidente.getProblemas().add(problemas.get(Integer.parseInt(opcion) - 1));

            if (!problemaNull()) {

                System.out.println("Problemas Agregados");
                System.out.println("");

                incidente.getProblemas().forEach(problema -> System.out.println(problema.getTipoProblema()));

                opcion = leer("Ingrese 1 para terminar o presione ENTER para continuar: ");

                if (opcion.equals("1")){

                    break;

                }

            }

        }while (true);
    }

    public void seleccionarTecnico() {
    }

    public void ingresarFechaResolucion() {
    }
}
