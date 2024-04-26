package ar.edu.unju.fi.exercise4.main;

import ar.edu.unju.fi.exercise4.common.Helper;
import ar.edu.unju.fi.exercise4.constant.Position;
import ar.edu.unju.fi.exercise4.model.Player;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

/**
 * 4. Uso de <code>enum</code>.
 *
 * @author Gutierrez Angel Gonzalo
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<>();
        byte selectedOption;

        try (Helper.SCANNER) {
            do {
                printMenu();
                selectedOption = Helper.getByte("Seleccione una opción");
                switch (selectedOption) {
                    case 1 -> addPlayer(players);
                    case 2 -> printAllPlayers(players);
                    case 3 -> modifyPlayerPosition(players);
                    case 4 -> deletePlayer(players);
                    case 5 -> Helper.printMessage("Saliendo del menú");
                    default -> Helper.printErrorMessage("Opción incorrecta, por favor intente una vez más");
                }
            } while (selectedOption != 5);
        } catch (Exception exception) {
            Helper.printErrorMessage("Ocurrió un error durante la ejecución del programa");
        } finally {
            Helper.printMessage("La aplicación finalizo con éxito");
        }
    }

    private static void addPlayer(ArrayList<Player> players) {
        Helper.printMessage("---- Ingrese los datos del jugador ----");
        players.add(createPlayer());
    }

    private static Player createPlayer() {
        final String name = getPlayerName();
        final String lastName = getPlayerLastName();
        final LocalDate birthdate = getPlayerBirthdate();
        final String nationality = getPlayerNationality();
        final double height = getPlayerHeight();
        final double weight = getPlayerWeight();
        final Position position = getPlayerPosition();
        return new Player(name, lastName, birthdate, nationality, height, weight, position);
    }

    private static void printAllPlayers(ArrayList<Player> players) {
        if (players.isEmpty()) {
            Helper.printErrorMessage("No se encuentra registrado ningún jugador");
        } else {
            Helper.printMessage("---- Mostrando todos los jugadores registrados ----");
            players.forEach(player -> Helper.printMessage(player.toString()));
        }
    }

    private static void modifyPlayerPosition(ArrayList<Player> players) {
        if (players.isEmpty()) {
            Helper.printErrorMessage("No se encuentra registrado ningún jugador");
        } else {
            final String name = getPlayerName();
            final String lastName = getPlayerLastName();
            Optional<Player> player = findPlayerByNameAndLastName(players, name, lastName);

            if (player.isPresent()) {
                System.out.println("---- Ingrese la nueva posición del jugador ----");
                player.get().setPosition(getPlayerPosition());
                Helper.printMessage(String.format("El jugador %s %s cambio su posición a %s",player.get().getName(), player.get().getLastName(), player.get().getPosition()));
            } else {
                Helper.printErrorMessage("El nombre y apellido ingresados no corresponde a ningún jugador registrado");
            }
        }
    }

    private static void deletePlayer(ArrayList<Player> players) {
        if (players.isEmpty()) {
            Helper.printErrorMessage("No se encuentra registrado ningún jugador");
        } else {
            final String name = getPlayerName();
            final String lastname = getPlayerLastName();
            boolean playerFound = false;
            Iterator<Player> playerIterator = players.iterator();

            while (playerIterator.hasNext()) {
                Player player = playerIterator.next();
                if (player.getName().equals(name) && player.getLastName().equals(lastname)) {
                    playerFound = true;
                    playerIterator.remove();
                    Helper.printMessage(String.format("El jugador %s %s ha sido eliminado", player.getName(), player.getLastName()));
                }
            }

            if (!playerFound) {
                Helper.printMessage("El nombre y apellido ingresado no corresponde a ningún jugador registrado");
            }
        }
    }

    private static Optional<Player> findPlayerByNameAndLastName(ArrayList<Player> players, final String name, final String lastName) {
        return players.stream().filter(player -> player.getName().equals(name) && player.getLastName().equals(lastName)).findFirst();
    }

    private static String getNotEmptyString(final String inputMessage, final String errorMessage) {
        String stringValue;
        final String regex = "^([a-zA-Z]+ )*[a-zA-Z]+$";

        while (true) {
            Helper.printInputMessage(inputMessage);
            stringValue = Helper.SCANNER.nextLine();

            if (stringValue.isEmpty() || !stringValue.matches(regex)) {
                Helper.printErrorMessage(errorMessage);
            } else {
                return stringValue;
            }
        }
    }

    private static String getPlayerName() {
        return getNotEmptyString("Ingrese el nombre", "Entrada no válida, por favor introduce un nombre").toLowerCase();
    }

    private static String getPlayerLastName() {
        return getNotEmptyString("Ingrese el apellido", "Entrada no válida, por favor introduce un apellido".toLowerCase());
    }

    private static LocalDate getPlayerBirthdate() {
        LocalDate birthdate;
        Helper.printMessage("Ingrese la fecha de nacimiento");

        while (true) {
            try {
                birthdate = LocalDate.of(
                        getPositiveIntegerNumber("Ingrese año"),
                        getPositiveIntegerNumber("Ingrese mes"),
                        getPositiveIntegerNumber("Ingrese día")
                );
                return birthdate;
            } catch (DateTimeException exception) {
                Helper.printErrorMessage("Entrada no válida, el valor no es una fecha");
            }
        }
    }

    private static String getPlayerNationality() {
        return getNotEmptyString("Ingrese nacionalidad", "Entrada no válida, por favor introduce una nacionalidad");
    }

    private static double getPositiveNumber(final String inputMessage) {
        double positiveDoubleValue;

        while (true) {
            positiveDoubleValue = Helper.getDouble(inputMessage);
            if (positiveDoubleValue > 0) {
                return positiveDoubleValue;
            } else {
                Helper.printErrorMessage("Entrada no válida, por favor introduce un número positivo");
            }
        }
    }

    private static int getPositiveIntegerNumber(final String inputMessage) {
        int positiveIntegerValue;

        while (true) {
            positiveIntegerValue = Helper.getInteger(inputMessage);
            if (positiveIntegerValue > 0) {
                return positiveIntegerValue;
            } else {
                Helper.printErrorMessage("Entrada no válida, por favor introduce un número entero positivo");
            }
        }
    }

    private static double getPlayerHeight() {
        return getPositiveNumber("Ingrese su altura");
    }

    private static double getPlayerWeight() {
        return getPositiveNumber("Ingrese su peso");
    }

    private static Position getPlayerPosition() {
        Position[] positions = Position.values();
        byte selectedOption;

        while (true) {
            printAvailableOptions(positions);
            selectedOption = Helper.getByte("Seleccione una opción");

            if (selectedOption >= 1 && selectedOption <= positions.length) {
                return positions[selectedOption - 1];
            } else {
                Helper.printErrorMessage("Opción incorrecta, por favor intente una vez más");
            }
        }
    }

    private static void printAvailableOptions(Position[] values) {
        byte i = 0;
        Helper.printMessage("---- Posiciones ----");

        for (Position option : values) {
            Helper.printMessage("\t\t" + ++i + " - " + option);
        }
    }

    private static void printMenu() {
        System.out.print("""
                ---- MENÚ ----
                1 - Alta de jugador
                2 - Mostrar todos los jugadores
                3 - Modificar la posición de un jugador
                4 - Eliminar un jugador
                5 - Salir
                """);
    }
}
