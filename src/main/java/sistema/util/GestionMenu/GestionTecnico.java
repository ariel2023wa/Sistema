package sistema.util.GestionMenu;

import sistema.entities.Especialidad;
import sistema.entities.Servicio;
import sistema.entities.Tecnico;
import sistema.service.Controladora;

import java.util.List;
import java.util.stream.Collectors;

import static sistema.util.UtilidadesGenerales.*;

public class GestionTecnico {

    private Tecnico tecnico = new Tecnico();

    private Controladora controladora = new Controladora();

    public void altaTecnico() {

        if (tecnicoNombre() && tecnicoLegajo() && tecnicoEspecialidad()) {

            controladora.crearTecnico(tecnico);

        }

    }

    private boolean tecnicoNombre() {
        boolean flag = false;

        do {

            String opcion = leer("Ingrese el nombre del tecnico (Ingrese 0 para cancelar): ");

            if (opcion.equals("0")) {

                System.out.println("Cancelando...");

                break;

            }

            if (validar(opcion, "^[a-zA-Z0-9\\s]+$")) {

                tecnico.setNombre(opcion);

                flag = true;

                System.out.println("Nombre del tecnico cargada");

                break;

            } else {

                System.out.println("Nombre invalido");

            }

        } while (true);


        return flag;
    }

    private boolean tecnicoLegajo() {

        boolean flag = false;

        do {

            String opcion = leer("Ingrese el numero de legajo del tecnico (Ingrese 0 para cancelar): ");

            if (opcion.equals("0")) {

                System.out.println("Cancelando...");

                break;

            }

            if (validar(opcion, "^\\d+$")) {

                Tecnico tecnicoExistente = controladora.buscarTecnicoPorLegajo(opcion);

                if (tecnicoExistente == null) {

                    tecnico.setNroLegajo(opcion);

                    flag = true;

                    System.out.println("Numero legajo cargado");

                    break;

                } else {

                    System.out.println("El numero de legajo ya existe");

                }

            } else {

                System.out.println("Numero legajo invalido");

            }

        } while (true);

        return flag;

    }

    private boolean tecnicoEspecialidad() {

        boolean flag = false;

        List<Especialidad> especialidades = controladora.listarEspecialidades();

        List<String> especialidadesString = especialidades.stream()
                .map(Especialidad::getDescripcion)
                .collect(Collectors.toList());

        especialidadesString.add("Cancelar");

        int opcion = imprimirValidarMenu(especialidadesString, "Seleccionar Especialidad");

        if (especialidadesString.get(opcion).equals("Cancelar")) {

            System.out.println("Cancelando...");

        } else {

            tecnico.setEspecialidad(especialidades.get(opcion));

            flag = true;

            System.out.println("Especialidad " + tecnico.getEspecialidad().getDescripcion() + " Agregada");

        }

        return flag;

    }


    public void bajaTecnico() {
    }

    public void modificarTecnico() {
    }
}
