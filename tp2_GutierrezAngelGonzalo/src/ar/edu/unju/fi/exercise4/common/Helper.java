package ar.edu.unju.fi.exercise4.common;

import java.util.Scanner;

/**
 * Permite introducir valores por consola de manera controlada.
 */
public class Helper {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static Byte getByte(final String inputMessage, final String errorMessage) {
        byte byteValue;

        while (true) {
            try {
                printInputMessage(inputMessage);
                byteValue = Byte.parseByte(SCANNER.nextLine());
                return byteValue;
            } catch (NumberFormatException exception) {
                printErrorMessage(errorMessage);
            }
        }
    }

    public static Byte getByte(final String inputMessage) {
        return getByte(inputMessage, "Entrada no válida, por favor introduce un número entero");
    }

    public static Integer getInteger(final String inputMessage, final String errorMessage) {
        int integerValue;

        while (true) {
            try {
                printInputMessage(inputMessage);
                integerValue = Integer.parseInt(SCANNER.nextLine());
                return integerValue;
            } catch (NumberFormatException exception) {
                printErrorMessage(errorMessage);
            }
        }
    }

    public static Integer getInteger(final String inputMessage) {
        return getInteger(inputMessage, "Entrada no válida, por favor introduce un número entero");
    }

    public static Double getDouble(final String inputMessage, final String errorMessage) {
        double doubleValue;

        while (true) {
            try {
                printInputMessage(inputMessage);
                doubleValue = Double.parseDouble(SCANNER.nextLine());
                return doubleValue;
            } catch (NumberFormatException exception) {
                printErrorMessage(errorMessage);
            }
        }
    }

    public static Double getDouble(final String inputMessage) {
        return getDouble(inputMessage, "Entrada no válida, por favor introduce un número");
    }

    public static void printMessage(final String message) {
        System.out.println(message);
    }

    public static void printInputMessage(final String message) {
        System.out.print(message + ": ");
    }

    public static void printErrorMessage(final String message) {
        System.out.println("ERROR: " + message);
    }
}
