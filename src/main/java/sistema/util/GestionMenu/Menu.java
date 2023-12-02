package sistema.util.GestionMenu;

import sistema.repositories.exceptions.NonexistentEntityException;

import static sistema.util.UtilidadesGenerales.*;

public class Menu {

    private GestionIncidente gi = new GestionIncidente();
    private GestionCliente gc = new GestionCliente();
    private GestionTecnico gt = new GestionTecnico();

    public void generarMenu() throws NonexistentEntityException {

        int opcion;

        String menu = "Menu Principal";

        String menuSeleccionado = "";

        Lista lista = new Lista();

        lista.cargarOpciones();

        do {

            opcion = imprimirValidarMenu(lista.obtenerLista().get(menu), menu);

            menuSeleccionado = lista.obtenerLista().get(menu).get(opcion);


            if (menuSeleccionado.equals("Salir")){

                System.out.println("Saliendo...");

                break;

            }

            if (menuSeleccionado.equals("Volver a Mesa de ayuda")) {
                gi = new GestionIncidente();
            }

            if (menuSeleccionado.contains("Volver a ")) {
                menuSeleccionado = menuSeleccionado.replace("Volver a ", "");
            }

            menuSeleccionado = gestionOpciones(menuSeleccionado, menu);

            menu = menuSeleccionado;

        }while(true);

    }

    public String gestionOpciones(String menuSeleccionado, String menu) throws NonexistentEntityException {

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
                gi.chequearTecnico();
                break;

            case "Ingesar fecha de resolucion":
                gi.chequearFecha();
                break;

            case "Confirmar incidente":
                gi.chequearIncidente();
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

            default:
                devolucion = menuSeleccionado;
        }

        return devolucion;

    }


}