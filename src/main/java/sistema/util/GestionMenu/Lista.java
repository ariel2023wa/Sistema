package sistema.util.GestionMenu;
import java.util.*;
import java.util.stream.IntStream;

public class Lista {
    private HashMap<String, List<String>> listaMenuTM = new HashMap<>();

    private String[][] opciones;

    public void cargarLista(String[][] menu) {
        Arrays.stream(menu).forEach(fila -> {
            String menuPrincipal = fila[0];
            listaMenuTM.put(menuPrincipal, new ArrayList<>());
            IntStream.range(1, fila.length).forEach(columna -> listaMenuTM.get(menuPrincipal).add(fila[columna]));
        });

    }
    
    public void cargarOpciones() {

        String[][] aux = {

                {"Menu Principal", "Mesa de ayuda", "Area comercial", "Area RRHH", "Salir"},
                {"Mesa de ayuda", "Ingresar incidente", "Volver a Menu Principal"},
                {"Ingresar incidente", "Buscar Cliente", "Seleccionar Servicio", "Ingresar descripcion", "Seleccionar problema",
                        "Seleccionar Tecnico", "Ingesar fecha de resolucion","Confirmar incidente", "Volver a Mesa de ayuda"},
                {"Area comercial", "Alta de cliente", "Baja de cliente", "Modificar cliente", "Volver a Menu Principal"},
                {"Area RRHH", "Gestion de tecnicos", "Emision de reportes", "Volver a Menu Principal"},
                {"Gestion de tecnicos", "Alta de tecnico", "Baja de tecnico", "Modificar tecnico", "Volver a Area RRHH"}

        };

        opciones = aux;

        cargarLista(opciones);

    }

    public HashMap<String, List<String>> obtenerLista() {

        return listaMenuTM;

    }

}
