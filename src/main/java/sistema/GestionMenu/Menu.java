package sistema.GestionMenu;

import static sistema.util.MetodosUtiles.*;

public class Menu {

    private GestionIncidente gi = new GestionIncidente();
    private GestionCliente gc = new GestionCliente();
    private GestionTecnico gt = new GestionTecnico();

    public String generarMenu(String menu) {

        String opcion;

        String menuSeleccionado = "";

        Lista lista = new Lista();

        lista.cargarOpciones();

        opcion = imprimirValidarMenu(lista.obtenerLista().get(menu), menu);

        menuSeleccionado = lista.obtenerLista().get(menu).get(Integer.parseInt(opcion)-1);


        if (menuSeleccionado.contains("Volver a ")) {
            menuSeleccionado = menuSeleccionado.replace("Volver a ", "");
        }

        menuSeleccionado = gestionOpciones(menuSeleccionado, menu);

        return menuSeleccionado;

    }

    public String gestionOpciones(String menuSeleccionado, String menu) {

        String devolucion = menu;

        switch (menuSeleccionado) {

            case "Buscar Cliente":
                gi.chequearCliente();
                break;

            case "Seleccionar Servicio":
                gi.chequearServicio();
                break;

            case "Ingresar descripcion":
                gi.chequearDesc();
                break;

            case "Seleccionar problema":
                gi.chequearProblema();
                break;

            case "Seleccionar Tecnico":
                gi.seleccionarTecnico();
                break;

            case "Ingesar fecha de resolucion":
                gi.ingresarFechaResolucion();
                break;

            case "Alta de cliente":
                gc.altaCliente();
                break;

            case "Baja de cliente":
                gc.bajaCliente();
                break;

            case "Modificar cliente":
                gc.modificarCliente();
                break;

            case "Alta de tecnico":
                gt.altaTecnico();
                break;

            case "Baja de tecnico":
                gt.bajaTecnico();
                break;

            case "Modificar tecnico":
                gt.modificarTecnico();
                break;

            case "Salir":
                System.out.println("Saliendo del sistema...");
                System.exit(0);
                break;

            default:
                devolucion = menuSeleccionado;
        }

        return devolucion;

    }


}