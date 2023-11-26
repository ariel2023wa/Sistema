package sistema.util;

import org.hibernate.Hibernate;
import sistema.entities.Cliente;
import sistema.entities.Incidente;
import sistema.entities.Servicio;
import sistema.repositories.ClienteRepository;
import sistema.service.Controladora;

import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {

    private static String leer() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Seleccione una opción: ");

        String opcion = scanner.nextLine();

        return opcion;
    }

    public static void menuPrincipal() {

        String opcion;

        do {
            System.out.println("=== Sistema de Reporte de Incidentes ===");
            System.out.println("");
            System.out.println("1. Mesa de ayuda");
            System.out.println("2. Area comercial");
            System.out.println("3. Area RRHH");
            System.out.println("4. Administracion general");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leer();

            switch (opcion) {
                case "1":
                    menuMesaDeAyuda();
                    break;
                case "2":
                    menuAreaComercial();
                    break;
                case "3":
                    menuAreaRRHH();
                    break;
                case "4":
                    menuAdministracionGeneral();
                    break;
                case "5":
                    System.out.println("Saliendo del Sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (!opcion.equals("5"));

    }

    //---------------Menu Primer nivel----------------------------------------------------------------------------------
    private static void menuMesaDeAyuda() {

        String opcion;

        do {
            System.out.println("=== Mesa de Ayuda ===");
            System.out.println("");
            System.out.println("1. Reportar Incidente");
            System.out.println("2. Consultar Reportes");
            System.out.println("3. Atras");
            System.out.print("Seleccione una opción: ");

            opcion = leer();

            switch (opcion) {
                case "1":
                    reportarIncidente();
                    break;
                case "2":
                    consultarReportes();
                    break;
                case "3":
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (!opcion.equals("3"));

    }

    private static void menuAreaComercial() {

        String opcion;

        do {
            System.out.println("=== Area Comercial ===");
            System.out.println("");
            System.out.println("1. Alta de Cliente");
            System.out.println("2. Modificacion de Cliente");
            System.out.println("3. Baja de Cliente");
            System.out.println("4. Atras");

            opcion = leer();

            switch (opcion) {
                case "1":
                    reportarIncidente();
                    break;
                case "2":
                    consultarReportes();
                    break;
                case "3":
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (!opcion.equals("3"));

    }

    private static void menuAreaRRHH() {

        String opcion;

        do {
            System.out.println("=== Area RRHH ===");
            System.out.println("");
            System.out.println("1. Alta de Tecnico");
            System.out.println("2. Modificacion de Tecnico");
            System.out.println("3. Baja de Tecnico");
            System.out.println("4. Atras");
            System.out.print("Seleccione una opción: ");

            opcion = leer();

            switch (opcion) {
                case "1":
                    reportarIncidente();
                    break;
                case "2":
                    consultarReportes();
                    break;
                case "3":
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (!opcion.equals("4"));
    }

    private static void menuAdministracionGeneral() {

        String opcion;

        do {
            System.out.println("=== Area Administracion General ===");
            System.out.println("");
            System.out.println("1. Gestion de Servicios");
            System.out.println("2. Gestion de Problemas");
            System.out.println("3. Gestion de Especialidades");
            System.out.println("4. Gestion de Medios de Notificaciones");
            System.out.println("5. Atras");

            opcion = leer();

            switch (opcion) {
                case "1":
                    reportarIncidente();
                    break;
                case "2":
                    consultarReportes();
                    break;
                case "3":
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (!opcion.equals("5"));

    }

    //---------------Menu Segundo nivel---------------------------------------------------------------------------------


    //---------------Menu Mesa de Ayuda

    private static void reportarIncidente() {

        Controladora controladora = new Controladora();

        String opcion;

        boolean clienteCargado = false;

        boolean servicioCargado = false;

        boolean descripcionCargada = false;

        Incidente incidente = new Incidente();


        do {
            System.out.println("=== Reportar Incidente ===");
            System.out.println("");
            System.out.println(" . Ingrese el CUIT sin guiones (ingrese 0 para Cancelar)");

            opcion = leer();

            boolean valCUIT = opcion.matches("\\d{11}");

            if (!opcion.equals("0")) {

                if (!valCUIT) {

                    System.out.println("Ingreso un CUIT incorrecto, intente nuevamente.");

                } else {

                    Cliente cliente = controladora.buscarClienteCUIT(opcion);


                    if (cliente != null) {

                        incidente.setCliente(cliente);

                        if (cliente.getServicios() != null) {

                            incidente.setCliente(cliente);

                            clienteCargado = true;

                            int cantServ = cliente.getServicios().size();

                            HashMap<Integer, Servicio> listaHM = (HashMap<Integer, Servicio>) cliente.getServicios()
                                    .stream().collect(Collectors.toMap(servicio -> cliente.getServicios().indexOf(servicio) + 1, servicio -> servicio));

                            do {

                                System.out.println(" . Elija un servicio para el reporte (ingrese 0 para Cancelar) ");

                                listaHM.forEach((clave, valor) -> System.out.println(clave + ". " + valor.getNombre()));

                                opcion = leer();

                                boolean valNum = opcion.matches("\\b(?:[1-9]\\d?|100)\\b");

                                if (valNum) {

                                    int num = Integer.parseInt(opcion);

                                    if (num <= cantServ) {

                                        incidente.setServicio(listaHM.get(num));

                                        System.out.println("Servicio Cargado");

                                        System.out.println(incidente.getServicio());

                                        servicioCargado = true;

                                        break;

                                    } else {

                                        System.out.println("Ingreso un numero incorrecto");

                                    }

                                } else if (opcion.equals("0")) {

                                    break;

                                } else {

                                    System.out.println("Ingreso un valor incorrecto");

                                }

                            } while (true);

                        } else {

                            System.out.println("El cliente no posee servicios");

                        }

                    } else {

                        System.out.println("El Cliente no ha sido encontrado");

                    }

                }
            }

            if (clienteCargado && servicioCargado) {

                do {

                    System.out.println(" . Ingrese una descripcion del incidente para el reporte (ingrese 0 para Cancelar) ");

                    opcion = leer();

                    boolean valDesc = opcion.matches("^(?!\\s*$).+");

                    if (!opcion.equals("0")) {

                        if (valDesc) {

                            incidente.setDescripcion(opcion);

                            descripcionCargada = true;

                            System.out.println("Descripcion cargada");

                            break;

                        } else {

                            System.out.println("No ingreso nada, vuelva a intentar");

                        }

                    } else {

                        break;

                    }

                } while (true);

            }




        } while (!opcion.equals("0"));

    }

    private static void consultarReportes() {

        System.out.println("Consultar Reportes");
        leer();

    }


}
