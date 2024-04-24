package ar.edu.unju.fi.exercise2.main;

import ar.edu.unju.fi.exercise2.common.Helper;
import ar.edu.unju.fi.exercise2.constant.Month;
import ar.edu.unju.fi.exercise2.model.Event;

import java.util.ArrayList;
import java.util.Optional;

/**
 * 2. Uso de <code>enum</code>.
 *
 * @author Gutierrez Angel Gonzalo
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<Event> events = new ArrayList<>();
        byte selectedOption;

        try (Helper.SCANNER){
            do {
                printMenu();
                selectedOption = Helper.getByte("Seleccione una opción");
                switch (selectedOption) {
                    case 1 -> addEvent(events);
                    case 2 -> printAllEvents(events);
                    case 3 -> deleteEvent(events);
                    case 4 -> modifyEvent(events);
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

    private static void addEvent(ArrayList<Event> events) {
        System.out.println("---- Ingrese los datos del evento ----");
        events.add(createEvent());
    }

    private static Event createEvent() {
        final int code = getEventCode("Ingrese el código");
        final Month month = getMonthEnum("Ingrese mes (ej. 4)");
        final int day = getEventDay("Ingrese un día (ej. 6)", month);
        final String detail = getEventDetail("Ingrese detalle");
        return new Event(code, month, day, detail);
    }

    private static void printAllEvents(ArrayList<Event> events) {
        if (events.isEmpty()) {
            Helper.printErrorMessage("No se encuentra registrado ningún evento");
        } else {
            Helper.printMessage("---- Mostrando todos los eventos registrados ----");
            events.forEach(event -> Helper.printMessage(event.toString()));

        }
    }

    private static void deleteEvent(ArrayList<Event> events) {
        if (events.isEmpty()) {
            Helper.printErrorMessage("No se encuentra registrado ningún evento");
        } else {
            final int code = getEventCode("Ingrese el código del evento a eliminar");
            Optional<Event> event = findEventByCode(events, code);

            if (event.isPresent()) {
                events.remove(event.get());
                Helper.printMessage("El evento código n.º " + event.get().getCode() + " ha sido eliminado");
            } else {
                Helper.printErrorMessage("El código ingresado no corresponde a ningún evento registrado");
            }
        }
    }

    private static void modifyEvent(ArrayList<Event> events) {
        if (events.isEmpty()) {
            Helper.printErrorMessage("No se encuentra registrado ningún evento");
        } else {
            final int code = getEventCode("Ingrese código del evento a modificar");
            Optional<Event> event = findEventByCode(events, code);

            if (event.isPresent()) {
                System.out.println("---- Ingrese los nuevos datos del evento ----");
                modifyEventData(event.get());
                Helper.printMessage("El evento código n.º " + event.get().getCode() + " ha sido modificado");
            } else {
                Helper.printErrorMessage("El código ingresado no corresponde a ningún evento registrado");
            }
        }
    }

    private static void modifyEventData(Event event) {
        event.setMonth(getMonthEnum("Ingrese mes (ej. 4)"));
        event.setDay(getEventDay("Ingrese un día (ej. 6)", event.getMonth()));
        event.setDetail(getEventDetail("Ingrese detalle"));
    }

    private static Optional<Event> findEventByCode(ArrayList<Event> events, final int code) {
        return events.stream().filter(event -> event.getCode().equals(code)).findFirst();
    }

    public static int getEventCode(final String message) {
        int positiveIntegerValue;

        while (true) {
            positiveIntegerValue = Helper.getInteger(message);
            if (positiveIntegerValue >= 0) {
                return positiveIntegerValue;
            } else {
                Helper.printErrorMessage("Entrada no válida, por favor introduce un número positivo o cero");
            }
        }
    }

    private static Month getMonthEnum(final String message) {
        int monthValue;

        while (true) {
            monthValue = Helper.getInteger(message);
            if (monthValue >= 1 && monthValue <= 12) {
                return Month.values()[monthValue - 1];
            } else {
                Helper.printErrorMessage("Entrada no válida, el valor no corresponde a un mes del año");
            }
        }
    }

    public static int getEventDay(final String message, final Month month) {
        int day;

        while (true) {
            day = Helper.getByte(message);
            if (day >= 1 && day <= 31 && dayOfMonthIsValid(day, month)) {
                return day;
            } else {
                Helper.printErrorMessage("Entrada no válida, el valor no corresponde a un día del mes");
            }
        }
    }

    public static boolean dayOfMonthIsValid(final int day, Month month) {
        switch (month) {
            case ENERO, MARZO, MAYO, JULIO, AGOSTO, OCTUBRE, DICIEMBRE -> { return day <= 31; }
            case FEBRERO -> { return day <= 29; }
            case ABRIL, JUNIO, SEPTIEMBRE, NOVIEMBRE -> { return day <= 30; }
            default -> throw new IllegalStateException("Entrada no válida: " + month);
        }
    }

    public static String getEventDetail(final String message) {
        String detail;

        while (true) {
            Helper.printInputMessage(message);
            detail = Helper.SCANNER.nextLine();

            if (detail.isEmpty()) {
                Helper.printErrorMessage("Entrada no válida. Por favor introduce una descripción.");
            } else {
                return detail;
            }
        }
    }

    private static void printMenu() {
        System.out.print("""
                ---- MENÚ ----
                1 - Crear efeméride
                2 - Mostrar efemérides
                3 - Eliminar efeméride
                4 - Modificar efeméride
                5 - Salir
                """);
    }
}