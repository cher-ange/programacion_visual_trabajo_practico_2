package ar.edu.unju.fi.exercise7.main;

import ar.edu.unju.fi.exercise7.common.Helper;
import ar.edu.unju.fi.exercise7.model.Category;
import ar.edu.unju.fi.exercise7.model.ManufacturingOrigin;
import ar.edu.unju.fi.exercise7.model.Product;

import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 7. Uso de <code>StreamCollection</code>.
 *
 * @author Gutierrez Angel Gonzalo
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Product> products = loadProducts();
        byte selectedOption;

        try (Helper.SCANNER) {
            do {
                selectedOption = selectMenuOption();
                switch (selectedOption) {
                    case 1 -> showAvailableProducts(products);
                    case 2 -> showRemainingProducts(products);
                    case 3 -> increaseAllProductPrices(products);
                    case 4 -> showAvailableProductsToSellByCategory(products);
                    case 5 -> sortProductsByPrice(products);
                    case 6 -> showProductNamesInUpperCase(products);
                    case 7 -> Helper.printMessage("Saliendo del menú");
                    default -> Helper.printErrorMessage("Opción incorrecta, por favor intente una vez más");
                }
            } while (selectedOption != 7);
        } catch (Exception e) {
            Helper.printErrorMessage("Ocurrió un error durante la ejecución del programa");
        } finally {
            Helper.printMessage("La aplicación finalizo con éxito");
        }
    }

    private static void showProductNamesInUpperCase(ArrayList<Product> products) {
        Function<Product, Product> nameToUpperCase = product -> {
            product.setDescription(product.getDescription().toUpperCase());
            return product;
        };
        products.stream().map(nameToUpperCase).forEach(product -> printProductInformation(product));
    }

    private static void sortProductsByPrice(ArrayList<Product> products) {
        Comparator<Product> comparePrice = Comparator.comparingDouble(Product::getUnitPrice).reversed();
        products.sort(comparePrice);
        products.forEach(product -> printProductInformation(product));
    }

    private static void showAvailableProductsToSellByCategory(ArrayList<Product> products) {
        Predicate<Product> productByCategory = product -> product.getCategory().equals(Category.ELECTROHOGAR) && product.getState().equals(true);

        products.stream().filter(productByCategory).forEach(product -> printProductInformation(product));
    }

    private static void increaseAllProductPrices(ArrayList<Product> products) {
        Function<Product, Product> increasePrice = product -> {
            product.setUnitPrice(product.getUnitPrice() + product.getUnitPrice() * 20 / 100);
            return product;
        };
        ArrayList<Product> productsWithIncreasedPrices = products.stream().map(increasePrice).collect(Collectors.toCollection(ArrayList::new));
        System.out.println("Los precios de los productos fueron incrementados un 20%");
    }

    private static void showRemainingProducts(ArrayList<Product> products) {
        Predicate<Product> productNotAvailable = product -> product.getState().equals(false);
        List<Product> remainingProducts = products.stream().filter(productNotAvailable).collect(Collectors.toList());

        remainingProducts.forEach(product -> printProductInformation(product));
    }

    private static void showAvailableProducts(ArrayList<Product> products) {
        Consumer<Product> printAvailableProduct = product -> {
            if (product.getState()) {
                printProductInformation(product);
            }
        };
        products.forEach(printAvailableProduct);
    }

    private static ArrayList<Product> loadProducts() {
        ArrayList<Product> products = new ArrayList<>();
        Random random = new Random();
        ManufacturingOrigin[] manufacturingOrigins = ManufacturingOrigin.values();
        Category[] categories = Category.values();
        Boolean[] availability = {true, false};

        for (int i = 1; i <= 15; i++) {
            products.add(new Product(
                    i,
                    "product" + i,
                    random.nextDouble(1000),
                    manufacturingOrigins[random.nextInt(manufacturingOrigins.length)],
                    categories[random.nextInt(categories.length)],
                    availability[random.nextInt(availability.length)]
                    )
            );
        }

        return products;
    }

    private static void printMenu() {
        Helper.printMessage("""
                ---- MENÚ ----
                1 - Mostrar productos
                2 - Mostrar productos restantes
                3 - Incrementar el precio de los productos
                4 - Mostrar los productos disponibles de la categoría Electrohogar
                5 - Ordenar los productos por precio de forma descendente
                6 - Mostrar los productos con los nombres en mayúsculas
                7 - Salir
                """);
    }

    private static byte selectMenuOption() {
        printMenu();
        return Helper.getByte("Seleccione una opción");
    }

    private static void printProductInformation(Product product) {
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("es", "AR"));
        numberFormat.setMaximumFractionDigits(2);
        System.out.printf("""
                ---- %s ----
                Código: %d
                Precio: $ %s
                Origen de fabricación: %s
                Categoría: %s
                Estado: %s%n
                """,
                product.getDescription(),
                product.getCode(),
                numberFormat.format(product.getUnitPrice()),
                product.getManufacturingOrigin(),
                product.getCategory(),
                product.getState() ? "Disponible" : "No disponible"
        );
    }
}
