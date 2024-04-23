package ar.edu.unju.fi.exercise1.common;

import java.util.Scanner;

/**
 * Permite introducir valores por consola de manera controlada.
 */
public class Helper {

    //#region Static objects
    public static final Scanner SCANNER = new Scanner(System.in);
    //#endregion

    //#region Character
    public static Character getCharacter(final String inputMessage, final String errorMessage) {
        char characterValue;
        final String regex = "^[0-9]$";

        while (true) {
            try {
                printInputMessage(inputMessage);
                characterValue = SCANNER.nextLine().charAt(0);

                if (Character.toString(characterValue).matches(regex)) {
                    return characterValue;
                } else {
                    printErrorMessage(errorMessage);
                }
            } catch (StringIndexOutOfBoundsException exception) {
                printErrorMessage("Entrada no válida. Por favor ingrese al menos un carácter.");
            }
        }
    }

    public static Character getCharacter(final String inputMessage) {
        return getCharacter(inputMessage, "Entrada no válida. Por favor ingrese solo caracteres numéricos.");
    }
    //#endregion

    //#region String
    public static String getString(final String inputMessage, final String errorMessage) {
        String stringValue;

        while (true) {
            printInputMessage(inputMessage);
            stringValue = SCANNER.nextLine();

            if (stringValue.isEmpty()) {
                printErrorMessage(errorMessage);
            } else {
                return stringValue;
            }
        }
    }

    public static String getString(final String inputMessage) {
        return getString(inputMessage, "Entrada no válida. Por favor introduce una descripción.");
    }
    //#endregion

    //#region Integer
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
        return getInteger(inputMessage, "Entrada no válida. Por favor ingrese un número entero.");
    }
    //#endregion

    //#region Double
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
        return getDouble(inputMessage, "Entrada no válida. Por favor ingrese un número.");
    }
    //#endregion

    //#region Messages
    public static void printMessage(final String message) {
        System.out.println(message);
    }

    public static void printInputMessage(final String message) {
        System.out.print(message + ": ");
    }

    public static void printErrorMessage(final String message) {
        System.out.println("ERROR: " + message);
    }
    //#endregion
}
