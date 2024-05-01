package ar.edu.unju.fi.exercise6.main;

import ar.edu.unju.fi.exercise6.interfaces.functionals.Converter;
import ar.edu.unju.fi.exercise6.model.DomesticFeline;
import ar.edu.unju.fi.exercise6.model.WildFeline;

/**
 * 6. Uso de <code>@FunctionalInterface</code>.
 *
 * @author Gutierrez Angel Gonzalo
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        WildFeline wildFeline = new WildFeline("Tanner", (byte) 20, 186f);

        Converter<WildFeline, DomesticFeline> converter = feline -> new DomesticFeline(
                feline.getName(),
                feline.getAge(),
                feline.getWeight()
        );

        if (Converter.isNotNull(wildFeline)) {
            DomesticFeline domesticFeline = converter.convert(wildFeline);
            converter.showObject(domesticFeline);
        } else {
            System.out.println("No es posible realizar la conversión");
        }
    }
}
