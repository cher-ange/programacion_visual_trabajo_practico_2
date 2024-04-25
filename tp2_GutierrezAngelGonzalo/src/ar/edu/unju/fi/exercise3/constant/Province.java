package ar.edu.unju.fi.exercise3.constant;

/**
 * @author Gutierrez Angel Gonzalo
 * @version 1.0
 */
public enum Province {
    JUJUY(797955, 53219d),
    SALTA(1440672, 155488d),
    TUCUMAN(1703186, 22524d),
    CATAMARCA(429556, 102602d),
    LA_RIOJA(384607, 89680d),
    SANTIAGO_DEL_ESTERO(1054028, 136351d);

    private final Integer population;
    private final Double area;

    Province(Integer population, Double area) {
        this.population = population;
        this.area = area;
    }

    public Integer getPopulation() {
        return population;
    }

    public Double getArea() {
        return area;
    }

    public double getPopulationDensity() {
        return population / area;
    }

    @Override
    public String toString() {
        return "Province{" +
                "population=" + population +
                ", area=" + area +
                "} " + super.toString();
    }
}
