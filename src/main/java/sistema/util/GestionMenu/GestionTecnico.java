package sistema.util.GestionMenu;

import sistema.entities.Especialidad;
import sistema.entities.Servicio;
import sistema.entities.Tecnico;
import sistema.repositories.exceptions.NonexistentEntityException;
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

            System.out.println("Tecnico " + tecnico.getNombre() + " creado");

        } else {

            System.out.println("El tecnico no fue creado");

        }

        tecnico = new Tecnico();

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

    public void bajaTecnico() throws NonexistentEntityException {

        do {

            String opcion = leer("Ingrese el numero de legajo del tecnico (Ingrese 0 para cancelar): ");

            if (opcion.equals("0")) {

                System.out.println("Cancelando...");

                break;

            }

            if (validar(opcion, "^\\d+$")) {

                tecnico = controladora.buscarTecnicoPorLegajo(opcion);

                if (tecnico == null) {

                    System.out.println("El tecnico no fue encontrado");

                } else {

                    controladora.bajarTecnico(tecnico.getIdTecnico());

                    System.out.println("El tecnico " + tecnico.getNombre() + " fue dado de baja");

                    tecnico = new Tecnico();

                    break;
                }

            } else {

                System.out.println("Numero legajo invalido");

            }

        } while (true);

    }

    public void modificarTecnico() throws Exception {

        boolean flag1 = false, flag2 = false, flag3 = false;

        do {

            String opcion = leer("Ingrese el numero de legajo del tecnico (Ingrese 0 para cancelar): ");

            if (opcion.equals("0")) {

                System.out.println("Cancelando...");

                break;

            }

            if (validar(opcion, "^\\d+$")) {

                tecnico = controladora.buscarTecnicoPorLegajo(opcion);

                if (tecnico == null) {

                    System.out.println("El tecnico no fue encontrado");

                } else {

                    do {

                        System.out.println("Que desea modificar?");
                        System.out.println("");
                        System.out.println("1. Tecnico: " + tecnico.getNombre());
                        System.out.println("2. Numero Legajo: " + tecnico.getNroLegajo());
                        System.out.println("3. Especialidad: " + tecnico.getEspecialidad().getDescripcion());
                        System.out.println("4. Terminar");

                        opcion = leer();

                        if (opcion.equals("4")) {

                            break;

                        }

                        switch (opcion) {
                            case "1":
                                flag1 = tecnicoNombre();
                                break;
                            case "2":
                                flag2 = tecnicoLegajo();
                                break;
                            case "3":
                                flag3 = tecnicoEspecialidad();
                                break;
                            default:
                                System.out.println("Opcion incorrecta");
                        }
                    }while (true);

                    break;

                }

            } else {

                System.out.println("Numero legajo invalido");

            }

        } while (true);

        if (!flag1 && !flag2 && !flag3){
            
            System.out.println("No se realizo ningun cambio");

        } else {

            controladora.modificarTecnico(tecnico);

            System.out.println("Los cambios fueron guardados");

        }

        tecnico = new Tecnico();

    }
}
