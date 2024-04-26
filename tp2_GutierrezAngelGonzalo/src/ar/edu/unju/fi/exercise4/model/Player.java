package ar.edu.unju.fi.exercise4.model;

import ar.edu.unju.fi.exercise4.constant.Position;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * @author Gutierrez Angel Gonzalo
 * @version 1.0
 */
public class Player {
    private String name;
    private String lastName;
    private LocalDate birthdate;
    private String nationality;
    private Double height;
    private Double weight;
    private Position position;

    public Player() {
    }

    public Player(
            String name,
            String lastName,
            LocalDate birthdate,
            String nationality,
            Double height,
            Double weight,
            Position position) {
        this.name = name;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.height = height;
        this.weight = weight;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getAge() {
        return Period.between(birthdate, LocalDate.now()).getYears();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Player player = (Player) object;
        return Objects.equals(name, player.name) && Objects.equals(lastName, player.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", nationality='" + nationality + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", position=" + position +
                '}';
    }
}
