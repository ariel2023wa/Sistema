package sistema.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.List;
import java.util.stream.IntStream;

public class UtilidadesGenerales {
    public static boolean validarFecha(String fecha) {
        try {
            LocalDate.parse(fecha);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

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

    public static int imprimirValidarMenu(List<String> lista, String menu){

        String opcion;

        int opcionInt;

        do {

            System.out.println("");
            System.out.println("=== " + menu + " ===");
            System.out.println("");

            IntStream.range(0, lista.size())
                    .forEach(i -> System.out.println((i + 1) + ". " + lista.get(i)));

            opcion = leer();

            if (validar(opcion, lista.size())){

                break;

            } else {

                System.out.println("Opcion incorrecta");

            }

        } while (true);

        opcionInt = Integer.parseInt(opcion) - 1;

        return opcionInt;

    }

}
