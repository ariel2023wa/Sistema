package sistema.util;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class MetodosUtiles {

    public static boolean validar(String opcion, String val) {

        return opcion.matches(val);

    }

    public static boolean validar(String opcion, int num) {

        return validar(opcion, "^[1-9][0-9]*$") && Integer.parseInt(opcion) <= num;

    }

    public static String leer(String mensaje) {

        System.out.print(mensaje);

        String opcion = ScannerInstancia.getInstance().nextLine();

        System.out.println("");

        return opcion;
    }

    public static String leer() {

        return leer("Seleccione una opción: ");

    }

    public static String imprimirValidarMenu(List<String> lista, String menu){

        int num;

        String opcion;

        do {

            System.out.println("");
            System.out.println("=== " + menu + " ===");
            System.out.println("");

            IntStream.range(0, lista.size())
                    .forEach(i -> System.out.println((i + 1) + ". " + lista.get(i)));

            num = lista.size();

            opcion = leer();

        } while (!validar(opcion, num));

        return opcion;

    }

}
