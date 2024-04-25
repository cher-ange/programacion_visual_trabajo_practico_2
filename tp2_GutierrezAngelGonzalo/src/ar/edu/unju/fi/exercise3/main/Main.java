package ar.edu.unju.fi.exercise3.main;

import ar.edu.unju.fi.exercise3.constant.Province;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

/**
 * 3. Uso de <code>enum</code>.
 *
 * @author Gutierrez Angel Gonzalo
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        Province[] provinces = Province.values();
        for (Province province : provinces) {
            printProvinceData(province);
        }
    }

    private static void printProvinceData(Province province) {
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("es", "AR"));
        numberFormat.setMaximumFractionDigits(2);

        String provinceData = String.format(
                "---- %s ----%n" + "Población: %s%n" + "Superficie: %s%n" + "Densidad poblacional: %s%n",
                province.name().replace("_", " "),
                numberFormat.format(province.getPopulation()),
                numberFormat.format(province.getArea()),
                numberFormat.format(province.getPopulationDensity()));

        System.out.println(provinceData);
    }
}
