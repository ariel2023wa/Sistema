import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {


        // Crear una lista y agregar el array
        List<Servicio> miLista = new ArrayList<>();
        miLista.add(new Servicio(1, "Tango"));
        miLista.add(new Servicio(2, "Windows"));
        miLista.add(new Servicio(3, "Linux"));

        HashMap<Integer, Servicio> listamap = (HashMap<Integer, Servicio>) miLista.stream()
                .collect(Collectors.toMap(servicio -> miLista.indexOf(servicio) + 1, servicio -> servicio));

        System.out.println("Start");
        System.out.println("");
        listamap.forEach((clave, valor) -> System.out.println(clave + ". " + valor.getNombre()));
        System.out.println("");
        System.out.println("Finish");

    }
}