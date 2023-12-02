package sistema.util.GestionMenu;

import sistema.entities.*;
import sistema.service.Controladora;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static sistema.util.UtilidadesGenerales.*;

public class GestionIncidente {

    private Incidente incidente = new Incidente();

    private Controladora contr = new Controladora();

    private boolean fecha = false;

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

        List<Servicio> servicios = incidente.getCliente().getServicios();

        List<String> serviciosNombres = servicios.stream()
                .map(Servicio::getNombre)
                .collect(Collectors.toList());

        serviciosNombres.add("Cancelar");

        int opcion = imprimirValidarMenu(serviciosNombres, "Seleccionar servicio");

        if (serviciosNombres.get(opcion).equals("Cancelar")) {

            System.out.println("Cancelando...");

        } else {

            incidente.setServicio(servicios.get(opcion));

            System.out.println("Servicio " + incidente.getServicio() + " Agregado");

        }

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

        Set<Problema> problemasIngresados = new HashSet<>();

        List<Problema> problemas = contr.listarProblemas();

        List<String> problemasNombres = problemas.stream()
                .map(Problema::getTipoProblema)
                .collect(Collectors.toList());

        problemasNombres.add("Terminar");

        do {

            int opcion = imprimirValidarMenu(problemasNombres, "Seleccionar problema");

            if (problemasNombres.get(opcion).equals("Terminar")) {

                break;

            } else {

                problemasIngresados.add(problemas.get(opcion));

                if (!problemasIngresados.isEmpty()) {

                    System.out.println("Problemas Agregados");
                    System.out.println("");

                    problemasIngresados.forEach(problema -> System.out.println(problema.getTipoProblema()));

                }

            }

        } while (true);

        if (!problemasIngresados.isEmpty()) {

            incidente.setProblemas(new ArrayList<>(problemasIngresados));

            System.out.println("Problemas cargados");

            incidente.getProblemas().forEach(problema -> System.out.println(problema));

        } else {


            System.out.println("No se cargo ningun problema");

        }

        System.out.println("");
        System.out.println("Terminando...");

    }

    public void chequearTecnico() {

        if (problemaNull()) {

            System.out.println("Se debe ingresar los problemas para poder elegir un tecnico");

        } else if (!tecnicoNull()) {

            System.out.println("Ya se ingreso un tecnico");

        } else {

            seleccionarTecnico();

        }

    }

    private void seleccionarTecnico() {

        List<Especialidad> listaEspecialidades = contr.listarEspecialidades();

        List<Especialidad> listaEspecialidadesSegunProblemas = listaEspecialidades.stream()
                .filter(especialidad -> especialidad.getProblemas().containsAll(incidente.getProblemas()))
                .collect(Collectors.toList());

        List<Tecnico> listaTecnicos = contr.listarTecnicosPorEspecialidades(listaEspecialidadesSegunProblemas);

        List<String> listaTecnicosString = listaTecnicos.stream()
                .map(Tecnico::getNombre)
                .collect(Collectors.toList());

        listaTecnicosString.add("Cancelar");

        int opcion = imprimirValidarMenu(listaTecnicosString, "Lista Tecnicos");

        if (listaTecnicosString.get(opcion).equals("Cancelar")) {

            System.out.println("Cancelando...");

        } else {

            incidente.setTecnico(listaTecnicos.get(opcion));

            System.out.println("Cliente " + incidente.getTecnico().getNombre() + " agregado");

        }

    }

    public void chequearFecha() {

        if (!fecha) {

            ingresarFechaResolucion();

        } else {

            System.out.println("La fecha ya fue ingresada");

        }

    }

    private void ingresarFechaResolucion() {

        do {

            String opcion = leer("Ingrese una fecha de posible resolucion (Ingrese 0 para cancelar): ");

            if (opcion.equals("0")) {

                System.out.println("Cancelando...");

                break;

            }

            if (validarFecha(opcion)) {

                incidente.setFechaPosResolucion(LocalDate.parse(opcion));

                fecha = true;

                System.out.println("Fecha agregada");

                break;

            } else {

                System.out.println("Ingreso una fecha no valida");

            }

        } while (true);
    }

    public void chequearIncidente() {

        if (!clienteNull() && !servicioNull() && !descNull() && !problemaNull() && !tecnicoNull() && fecha) {

            confirmarIncidente();


        } else {

            System.out.println("El incidente no se puede confirmar porque no esta completo");

        }

    }

    private void confirmarIncidente() {

        incidente.setEstado(new Abierto());

        contr.crearIncidente(incidente);

        System.out.println("Incidente creado");

        incidente = new Incidente();

    }
}
