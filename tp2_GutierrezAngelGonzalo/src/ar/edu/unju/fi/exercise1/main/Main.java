package ar.edu.unju.fi.exercise1.main;

import ar.edu.unju.fi.exercise1.common.Helper;
import ar.edu.unju.fi.exercise1.model.Category;
import ar.edu.unju.fi.exercise1.model.ManufacturingOrigin;
import ar.edu.unju.fi.exercise1.model.Product;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * 1. Uso de <code>enum</code>.
 *
 * @author Gutierrez Angel Gonzalo
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {

        ArrayList<Product> products = new ArrayList<>();
        char selectedOption;

        try (Helper.SCANNER) {
            do {
                printMenu();
                selectedOption = Helper.getCharacter("Seleccione una opción");
                switch (selectedOption) {
                    case '1' -> products.add(createProduct());
                    case '2' -> printAllProducts(products);
                    case '3' -> modifyProductByCode(products);
                    case '4' -> Helper.printMessage("Saliendo del menú");
                    default -> Helper.printErrorMessage("Opción incorrecta. Por favor intente una vez más.");
                }
            } while (selectedOption != '4');
        } catch (InputMismatchException exception) {
            Helper.printErrorMessage("Ocurrió un error durante la ejecución del programa");
        } finally {
            Helper.printMessage("La aplicación finalizo con éxito");
        }
    }

    //#region Options
    private static Product createProduct() {
        Product product;
        Helper.printMessage("---- Ingrese los datos del producto ----");
        return new Product(
                Helper.getInteger("Ingrese el código"),
                Helper.getString("Ingrese la descripción"),
                Helper.getDouble("Ingrese el precio por unidad"),
                getProperty("Ingrese origen de fabricación", ManufacturingOrigin.values()),
                getProperty("Ingrese categoría", Category.values())
        );
    }

    private static void printAllProducts(ArrayList<Product> products) {
        Helper.printMessage("Mostrando todos los productos disponibles");
        for (Product product : products) {
            Helper.printMessage(product.toString());
        }
    }

    private static void modifyProductByCode(ArrayList<Product> products) {

        int code;
        Product product;

        if (products.isEmpty()) {
            Helper.printErrorMessage("No se encuentran productos registrados");
        } else {
            Helper.printMessage("Actualmente se encuentran disponibles " + products.size() + " producto/s");
            code = Helper.getInteger("Ingrese código del producto");
            product = findProductByCode(products, code);
            inputProductData(product);
        }
    }
    //#endregion

    //#region Extra
    private static void printMenu() {
        System.out.print("""
                ---- MENÚ ----
                1 - Crear producto
                2 - Mostrar productos
                3 - Modificar producto
                4 - Salir
                """);
    }

    private static <T extends Enum<T>> void printAvailableOptions(T[] values) {
        byte i = 0;

        if (values[0] instanceof ManufacturingOrigin) {
            Helper.printMessage("---- Origen de fabricación ----");
        }

        if (values[0] instanceof Category) {
            Helper.printMessage("---- Categoría ----");
        }

        for (T option : values) {
            Helper.printMessage("\t\t" + ++i + " - " + option);
        }
    }

    private static <T extends Enum<T>> T getProperty(final String message, T[] enumValues) {
        int selectedOption;

        while (true) {
            printAvailableOptions(enumValues);
            selectedOption = Helper.getInteger("Seleccione una opción");

            if (selectedOption >= 1 && selectedOption <= enumValues.length) {
                return enumValues[selectedOption - 1];
            } else {
                Helper.printErrorMessage("Opción incorrecta. Por favor intente una vez más.");
            }
        }
    }

    private static void inputProductData(Product product) {
        product.setDescription(Helper.getString("Ingrese la descripción"));
        product.setUnitPrice(Helper.getDouble("Ingrese el precio por unidad"));
        product.setManufacturingOrigin(getProperty("Ingrese origen de fabricación", ManufacturingOrigin.values()));
        product.setCategory(getProperty("Ingrese categoría", Category.values()));
    }

    private static Product findProductByCode(ArrayList<Product> products, final int code) {
        while (true) {
            try {
                return products.get(code - 1);
            } catch (IndexOutOfBoundsException exception) {
                Helper.printErrorMessage("El producto no se encuentra disponible. Por favor ingrese otro código.");
            }
        }
    }
    //#endregion
}
