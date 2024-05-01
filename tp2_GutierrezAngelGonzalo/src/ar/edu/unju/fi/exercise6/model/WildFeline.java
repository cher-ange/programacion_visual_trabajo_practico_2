package ar.edu.unju.fi.exercise6.model;

/**
 * @author Gutierrez Angel Gonzalo
 * @version 1.0
 */
public class WildFeline {
    private String name;
    private Byte age;
    private Float weight;

    public WildFeline() {
    }

    public WildFeline(String name, Byte age, Float weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "WildCat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }
}
