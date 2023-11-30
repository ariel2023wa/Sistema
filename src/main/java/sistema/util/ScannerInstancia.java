package sistema.util;

import java.util.Scanner;

public class ScannerInstancia {

    private static Scanner scanner;

    private ScannerInstancia() {

    }

    public static Scanner getInstance(){

        if (scanner== null) {
            scanner = new Scanner(System.in);
        }

        return scanner;

    }


}
