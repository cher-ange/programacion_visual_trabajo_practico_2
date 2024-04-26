package ar.edu.unju.fi.exercise1.main;

import ar.edu.unju.fi.exercise1.common.Helper;
import ar.edu.unju.fi.exercise1.model.Category;
import ar.edu.unju.fi.exercise1.model.ManufacturingOrigin;
import ar.edu.unju.fi.exercise1.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 * 1. Uso de <code>enum</code>.
 *
 * @author Gutierrez Angel Gonzalo
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        byte selectedOption;

        try (Helper.SCANNER) {
            do {
                printMenu();
                selectedOption = Helper.getByte("Seleccione una opción");
                switch (selectedOption) {
                    case 1 -> addProduct(products);
                    case 2 -> printAllProducts(products);
                    case 3 -> modifyProduct(products);
                    case 4 -> Helper.printMessage("Saliendo del menú");
                    default -> Helper.printErrorMessage("Opción incorrecta, por favor intente una vez más");
                }
            } while (selectedOption != 4);
        } catch (Exception exception) {
            Helper.printErrorMessage("Ocurrió un error durante la ejecución del programa");
        } finally {
            Helper.printMessage("La aplicación finalizo con éxito");
        }
    }

    private static void addProduct(ArrayList<Product> products) {
        Helper.printMessage("---- Ingrese los datos del producto ----");
        products.add(createProduct());
    }

    private static Product createProduct() {
        final int code = getProductCode("Ingrese el código");
        final String description = getProductDescription("Ingrese la descripción");
        final double unitPrice = getProductUnitPrice("Ingrese el precio por unidad");
        final ManufacturingOrigin manufacturingOrigin = getProductManufacturingOrigin(
                "Ingrese origen de fabricación",
                ManufacturingOrigin.values());
        final Category category = getProductCategory("Ingrese categoría", Category.values());
        return new Product(code, description, unitPrice, manufacturingOrigin, category);
    }

    private static void printAllProducts(ArrayList<Product> products) {
        if (products.isEmpty()) {
            Helper.printErrorMessage("No se encuentra registrado ningún producto");
        } else {
            Helper.printMessage("---- Mostrando todos los productos registrados ----");
            products.forEach(product -> Helper.printMessage(product.toString()));
        }
    }

    private static void modifyProduct(ArrayList<Product> products) {
        if (products.isEmpty()) {
            Helper.printErrorMessage("No se encuentra registrado ningún producto");
        } else {
            final int code = getProductCode("Ingrese código del producto a modificar");
            Optional<Product> product = findProductByCode(products, code);

            if (product.isPresent()) {
                System.out.println("---- Ingrese los nuevos datos del producto ----");
                modifyProductData(product.get());
                Helper.printMessage("El producto código n.º " + product.get().getCode() + " ha sido modificado");
            } else {
                Helper.printErrorMessage("El código ingresado no corresponde a ningún producto registrado");
            }
        }
    }

    private static void modifyProductData(Product product) {
        product.setDescription(getProductDescription("Ingrese la descripción"));
        product.setUnitPrice(getProductUnitPrice("Ingrese el precio por unidad"));
        product.setManufacturingOrigin(getProductManufacturingOrigin("Ingrese origen de fabricación", ManufacturingOrigin.values()));
        product.setCategory(getProductCategory("Ingrese categoría", Category.values()));
    }

    private static Optional<Product> findProductByCode(ArrayList<Product> products, final int code) {
        return products.stream().filter(product -> product.getCode().equals(code)).findFirst();
    }

    private static int getProductCode(final String message) {
        int positiveIntegerValue;

        while (true) {
            positiveIntegerValue = Helper.getInteger(message);
            if (positiveIntegerValue > 0) {
                return positiveIntegerValue;
            } else {
                Helper.printErrorMessage("Entrada no válida, por favor introduce un número positivo");
            }
        }
    }

    private static String getProductDescription(final String message) {
        String description;

        while (true) {
            Helper.printInputMessage(message);
            description = Helper.SCANNER.nextLine();

            if (description.isEmpty()) {
                Helper.printErrorMessage("Entrada no válida, por favor introduce una descripción");
            } else {
                return description;
            }
        }
    }

    private static double getProductUnitPrice(final String message) {
        double positiveIntegerValue;

        while (true) {
            positiveIntegerValue = Helper.getDouble(message);
            if (positiveIntegerValue > 0) {
                return positiveIntegerValue;
            } else {
                Helper.printErrorMessage("Entrada no válida, por favor introduce un número positivo");
            }
        }
    }

    private static ManufacturingOrigin getProductManufacturingOrigin(final String message, ManufacturingOrigin[] values) {
        return getProductEnum(message, values);
    }

    private static Category getProductCategory(final String message, Category[] values) {
        return getProductEnum(message, values);
    }

    private static <T extends Enum<T>> void printAvailableOptions(T[] values) {
        byte i = 0;

        try {
            if (values[0] instanceof ManufacturingOrigin) {
                Helper.printMessage("---- Origen de fabricación ----");
            }

            if (values[0] instanceof Category) {
                Helper.printMessage("---- Categoría ----");
            }

            for (T option : values) {
                Helper.printMessage("\t\t" + ++i + " - " + option);
            }
        } catch (IllegalStateException exception) {
            Helper.printErrorMessage("Entrada no válida: " + values.getClass());
        }
    }

    private static <T extends Enum<T>> T getProductEnum(final String message, T[] enumValues) {
        byte selectedOption;

        while (true) {
            printAvailableOptions(enumValues);
            selectedOption = Helper.getByte("Seleccione una opción");

            if (selectedOption >= 1 && selectedOption <= enumValues.length) {
                return enumValues[selectedOption - 1];
            } else {
                Helper.printErrorMessage("Opción incorrecta, por favor intente una vez más");
            }
        }
    }

    private static void printMenu() {
        System.out.print("""
                ---- MENÚ ----
                1 - Crear producto
                2 - Mostrar productos
                3 - Modificar producto
                4 - Salir
                """);
    }
}
