package sistema.util.GestionMenu;

import org.checkerframework.checker.units.qual.C;
import sistema.entities.Cliente;
import sistema.entities.Problema;
import sistema.entities.Servicio;
import sistema.repositories.exceptions.NonexistentEntityException;
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

            System.out.println("El cliente " + cliente.getRazonSocial() + " fue creado");

        } else {

            System.out.println("El cliente no fue creado");

        }

    }

    private boolean clienteRazonSocial() {

        boolean flag = false;

        do {

            String opcion = leer("Ingrese la razon social del cliente (Ingrese 0 para cancelar): ");

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

            String opcion = leer("Ingrese el CUIT del cliente (Ingrese 0 para cancelar): ");

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

            int opcion = imprimirValidarMenu(ServiciosNombres, "Seleccionar servicio: ");

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

            cliente.getServicios().forEach(servicio -> System.out.println(servicio.getNombre()));

        } else {


            System.out.println("No se cargo ningun servicio");

        }

        System.out.println("");
        System.out.println("Terminando...");
        System.out.println("");

        return flag;

    }


    public void bajaCliente() throws NonexistentEntityException {

        do {

            String opcion = leer("Ingrese el CUIT del cliente (Ingrese 0 para cancelar): ");

            if (opcion.equals("0")) {

                System.out.println("Cancelando...");

                break;

            }

            if (validar(opcion, "\\d{11}")) {

                cliente = controladora.buscarClienteCUIT(opcion);

                if (cliente == null) {

                    System.out.println("El cliente no fue encontrado");

                } else {

                    controladora.bajarCliente(cliente.getIdCliente());

                    System.out.println("El cliente " + cliente.getRazonSocial() + " fue dado de baja");

                    break;
                }

            } else {

                System.out.println("CUIT invalido");

            }

        } while (true);

    }

    public void modificarCliente() {

        boolean flag1 = false, flag2 = false, flag3 = false;

        do {

            String opcion = leer("Ingrese el CUIT del cliente (Ingrese 0 para cancelar): ");

            if (opcion.equals("0")) {

                System.out.println("Cancelando...");

                break;

            }

            if (validar(opcion, "\\d{11}")) {

                cliente = controladora.buscarClienteCUIT(opcion);

                if (cliente == null) {

                    System.out.println("El cliente no fue encontrado");

                } else {

                    do {

                        System.out.println("Que desea modificar?");
                        System.out.println();
                        System.out.println("1. Cliente: " + cliente.getRazonSocial());
                        System.out.println("2. CUIT: " + cliente.getCuit());
                        System.out.println("3. Servicios: ");
                        cliente.getServicios().forEach(servicio -> System.out.println(servicio.getNombre()));
                        System.out.println("4. Terminar");

                        opcion = leer();

                        if (opcion.equals("4")) {

                            break;

                        }

                        switch (opcion) {
                            case "1":
                                flag1 = clienteRazonSocial();
                                break;
                            case "2":
                                flag2 = clienteCUIT();
                                break;
                            case "3":
                                flag3 = clienteServicios();
                                break;
                            default:
                                System.out.println("Opcion incorrecta");
                        }
                    }while (true);

                    break;

                }

            } else {

                System.out.println("CUIT invalido");

            }

        } while (true);

        if (!flag1 && !flag2 && !flag3){

            System.out.println("No se realizo ningun cambio");

        } else {

            System.out.println("Los cambios fueron guardados");

        }
        
    }
}
