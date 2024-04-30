package ar.edu.unju.fi.exercise5.main;

import ar.edu.unju.fi.exercise5.common.Helper;
import ar.edu.unju.fi.exercise5.model.*;

import java.io.FilterOutputStream;
import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;

/**
 * 5. Uso de <code>interface</code>.
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
                selectedOption = getMenuOption();
                switch (selectedOption) {
                    case 1 -> printAllProducts(products);
                    case 2 -> makeAPurchase(products);
                    case 3 -> Helper.printMessage("Saliendo del menú");
                    default -> Helper.printErrorMessage("Opción incorrecta, por favor intente una vez más");
                }
            } while (selectedOption != 3);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            Helper.printErrorMessage("Ocurrió un error durante la ejecución del programa");
        } finally {
            Helper.printMessage("La aplicación finalizo con éxito");
        }
    }

    private static void makeAPurchase(ArrayList<Product> products) {
        ArrayList<Product> purchasedProducts = new ArrayList<>();
        Optional<Product> productFound;
        byte selectedOperationOption;

        do {
            printAllProducts(products);

            int selectedProduct = Helper.getInteger("Seleccione un producto por su código");
            productFound = products.stream().filter(product -> product.getCode() == selectedProduct && product.getState().equals(true)).findFirst();

            if (productFound.isPresent()) {
                purchasedProducts.add(productFound.get());
                Helper.printMessage("El producto código n.º " + productFound.get().getCode() + " ha sido añadido al carrito");

            } else {
                Helper.printErrorMessage("El producto código n.º " + selectedProduct + " no se encuentra disponible");
            }
            selectedOperationOption = getOperation();
        } while (selectedOperationOption != 2);

        receivePaymentReceipt(purchasedProducts);
    }

    private static void receivePaymentReceipt(ArrayList<Product> purchasedProducts) {
        byte selectedPaymentOption;
        double sumOfPurchasedProducts;
        selectedPaymentOption = getPaymentMethod();

        if (purchasedProducts.isEmpty()) {
            Helper.printMessage("El carrito esta vacío");
        } else {
            sumOfPurchasedProducts = purchasedProducts.stream().mapToDouble(Product::getUnitPrice).sum();
            if (selectedPaymentOption == 1) {
                CashPayment cashPayment = new CashPayment();
                cashPayment.makePayment(sumOfPurchasedProducts);
                cashPayment.printRecipe();
            } else {
                CardPayment cardPayment = new CardPayment();
                cardPayment.setCardNumber(getCardNumber());
                cardPayment.makePayment(sumOfPurchasedProducts);
                cardPayment.printRecipe();
            }
        }
    }

    private static String getCardNumber() {
        String cardNumber;

        while (true) {
            Helper.printInputMessage("Ingrese el número de su tarjeta");
            cardNumber = Helper.SCANNER.nextLine();
            if (cardNumber.matches("[0-9]*")) {
                return cardNumber;
            } else {
                Helper.printErrorMessage("Entrada no válida, por favor intente una vez más");
            }
        }
    }

    private static void printOptions(final String title, String[] options) {
        StringBuilder stringBuilder = new StringBuilder();
        final String decorator = "----";
        final String spacer = " - ";
        final String newline = "\n";
        int i = 0;

        stringBuilder.append(decorator).append(" ").append(title).append(" ").append(decorator).append(newline);
        while (i < options.length) {
            if (i == options.length - 1) {
                stringBuilder.append(i + 1).append(spacer).append(options[i]);
                break;
            }
            stringBuilder.append(i + 1).append(spacer).append(options[i]).append(newline);
            i++;
        }

        Helper.printMessage(stringBuilder.toString());
    }

    private static byte getOperation() {
        final String title = "Operación";
        String[] operations = {"Continuar comprando", "Terminar compra"};

        return getSelectedOption(
                title,
                operations,
                "Seleccione una operación",
                "Operación incorrecta, intente una vez más"
        );
    }

    private static byte getPaymentMethod() {
        final String title = "Método de pago";
        String[] paymentMethods = {"Pago efectivo", "Pago con tarjeta"};

        return getSelectedOption(
                title,
                paymentMethods,
                "Seleccione una forma de pago",
                "Método de pago incorrecto, intente una vez más"
        );
    }

    private static byte getSelectedOption(final String title, String[] options, final String inputMessage, final String errorMessage) {
        byte selectedOption;
        while (true) {
            printOptions(title, options);
            selectedOption = Helper.getByte(inputMessage);
            if (selectedOption <= options.length) {
                return selectedOption;
            } else {
                Helper.printErrorMessage(errorMessage);
            }
        }
    }

    private static void printAllProducts(ArrayList<Product> products) {
        if (products.isEmpty()) {
            Helper.printErrorMessage("No se encuentra registrado ningún producto");
        } else {
            Helper.printMessage("---- Mostrando todos los productos registrados ----");
            products.stream()
                    .filter(product -> product.getState().equals(true))
                    .forEach(product -> printProductInformation(product));
        }
    }

    private static void printProductInformation(Product product) {
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("es", "AR"));
        numberFormat.setMaximumFractionDigits(2);

        System.out.printf("""
                ---- Información del producto ---
                Código: %d
                Descripción: %s
                Precio por unidad: $ %s
                Origen de fabricación: %s
                Categoría: %s%n
                """,
                product.getCode(),
                product.getDescription(),
                numberFormat.format(product.getUnitPrice()),
                product.getManufacturingOrigin(),
                product.getCategory());
    }

    private static ArrayList<Product> loadProducts() {
        ArrayList<Product> products = new ArrayList<>();
        Random random = new Random();
        ManufacturingOrigin[] manufacturingOrigins = ManufacturingOrigin.values();
        Category[] categories = Category.values();
        String[] availability = {"true", "false"};

        for (int i = 1; i <= 15; i++) {
            products.add(new Product(
                    i,
                    "product" + i,
                    random.nextDouble(1000),
                    manufacturingOrigins[random.nextInt(manufacturingOrigins.length)],
                    categories[random.nextInt(categories.length)],
                    Boolean.parseBoolean(availability[random.nextInt(availability.length)])
                    )
            );
        }

        return products;
    }

    private static byte getMenuOption() {
        String title = "MENÚ";
        String[] menuOptions = {"Mostrar productos", "Realizar compra", "Salir"};
        printOptions(title, menuOptions);
        return Helper.getByte("Seleccione una opción");
    }
}
