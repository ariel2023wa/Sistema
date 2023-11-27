package sistema.util;

import org.hibernate.Hibernate;
import sistema.entities.*;
import sistema.repositories.ClienteRepository;
import sistema.service.Controladora;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class Menu {

    private static String leer() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Seleccione una opción: ");

        String opcion = scanner.nextLine();

        return opcion;
    }

    public static boolean validarFecha(String fecha) {
        try {
            LocalDate.parse(fecha);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
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

        boolean validar = false;

        boolean clienteCargado = false;

        boolean servicioCargado = false;

        boolean descripcionCargada = false;

        boolean problemaCargado = false;

        boolean tecnicoCargado = false;

        boolean fechacargada = false;

        Incidente incidente = new Incidente();


        do {
            System.out.println("=== Reportar Incidente ===");
            System.out.println("");
            System.out.println(" . Ingrese el CUIT sin guiones (ingrese 0 para Cancelar)");

            opcion = leer();

            validar = opcion.matches("\\d{11}");

            if (!opcion.equals("0")) {

                if (!validar) {

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

                                validar = opcion.matches("\\b(?:[1-9]\\d?|100)\\b");

                                if (validar) {

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

                    validar = opcion.matches("^(?!\\s*$).+");

                    if (!opcion.equals("0")) {

                        if (validar) {

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

            if (descripcionCargada) {

                List<Problema> listaProblema = controladora.listarProblemas();

                if (listaProblema.isEmpty()) {

                    System.out.println("No se encontraron problemas en la base de datos");

                } else {

                    HashMap<Integer, Problema> listMapProblema = (HashMap<Integer, Problema>) listaProblema.stream()
                            .collect(Collectors.toMap(servicio -> listaProblema.indexOf(servicio) + 1, problema -> problema));

                    Set<Problema> problemaSet = new HashSet<>();

                    do {

                        System.out.println(". Ingrese el problema (Ingrese 0 para terminar)");
                        System.out.println("");

                        listMapProblema.forEach((clave, valor) -> System.out.println(clave + ". " + valor.getTipoProblema()));

                        if (!problemaSet.isEmpty()) {

                            System.out.println("");

                            System.out.println(". Problemas cargados:");

                            problemaSet.stream()
                                    .map(Problema::getTipoProblema)
                                    .forEach(System.out::println);

                        }

                        opcion = leer();

                        validar = opcion.matches("\\b(?:[1-9]\\d?|100)\\b");

                        if (!opcion.equals("0")) {

                            if (validar) {

                                int num = Integer.parseInt(opcion);

                                int cantServ = listaProblema.size();

                                if (num <= cantServ) {

                                    problemaSet.add(listMapProblema.get(num));

                                    System.out.println("Problema cargado");

                                    problemaCargado = true;

                                } else {

                                    System.out.println("Ingreso un numero incorrecto");

                                }


                            } else {

                                System.out.println("Ingreso un valor incorrecto");

                            }


                        } else {

                            System.out.println("Saliendo...");

                            break;

                        }

                    } while (true);

                    if (problemaCargado) {

                        incidente.setProblemas(new ArrayList<>(problemaSet));

                        incidente.getProblemas().stream().forEach(System.out::println);

                    }

                }


            }

            if (problemaCargado) {

                List<Especialidad> listaEspecialidades = controladora.listarEspecialidades();

                List<Especialidad> listaEspecialidadesEnc = listaEspecialidades.stream()
                        .filter(especialidad -> especialidad.getProblemas().containsAll(incidente.getProblemas()))
                        .collect(Collectors.toList());

                List<Tecnico> listaTecnicos = controladora.listarTecnicosPorEspecialidades(listaEspecialidadesEnc);


                HashMap<Integer, Tecnico> listaTecnicosMap = (HashMap<Integer, Tecnico>) listaTecnicos.stream()
                        .collect(Collectors.toMap(tecnico -> listaTecnicos.indexOf(tecnico) + 1, tecnico -> tecnico));

                do {


                    System.out.println(". Elija el tecnico para resolver el incidente (Ingrese 0 para cancelar)");
                    System.out.println("");

                    listaTecnicosMap.forEach((clave, valor) -> System.out.println(clave + ". " + valor.getNombre()));

                    opcion = leer();

                    validar = opcion.matches("\\b(?:[1-9]\\d?|100)\\b");

                    if (!opcion.equals("0")) {


                        if (validar) {

                            int num = Integer.parseInt(opcion);

                            int cantServ = listaTecnicos.size();

                            if (num <= cantServ) {

                                incidente.setTecnico(listaTecnicosMap.get(num));

                                tecnicoCargado = true;

                                System.out.println("Tecnico cargado");

                                break;

                            } else {

                                System.out.println("Ingreso un numero incorrecto");

                            }

                        } else {

                            System.out.println("Ingreso un valor incorrecto");

                        }

                    } else {

                        System.out.println("Saliendo...");

                        break;
                    }

                } while (true);

            }

            if (tecnicoCargado) {

                do {

                    System.out.println("Ingrese una fecha de posible resolucion (Ingrese 0 para cancelar)");

                    opcion = leer();

                    if (!opcion.equals("0")) {

                        if (validarFecha(opcion)) {

                            incidente.setFechaPosResolucion(LocalDate.parse(opcion));

                            fechacargada = true;

                            System.out.println("Fecha cargada");

                            break;

                        } else {

                            System.out.println("Ingreso una fecha incorrecta");

                        }

                    } else {

                        System.out.println("Saliendo...");

                        break;

                    }




                } while (true);

            }

            if (fechacargada) {




                System.out.println("Informar al cliente que el incidente ha sido ingresado y su fecha de posible resolucion es: " + incidente.getFechaPosResolucion());

            }

        } while (!opcion.equals("0"));

    }

    private static void consultarReportes() {

        System.out.println("Consultar Reportes");
        leer();

    }
}

