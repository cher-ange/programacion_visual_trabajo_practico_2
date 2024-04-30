package ar.edu.unju.fi.exercise5.model;

import java.util.Objects;

/**
 * @author Gutierrez Angel Gonzalo
 * @version 1.0
 */
public class Product {
    private Integer code;
    private String description;
    private Double unitPrice;
    private ManufacturingOrigin manufacturingOrigin;
    private Category category;
    private Boolean state = true;

    public Product() {
    }

    public Product(
            Integer code,
            String description,
            Double unitPrice,
            ManufacturingOrigin manufacturingOrigin,
            Category category) {
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.manufacturingOrigin = manufacturingOrigin;
        this.category = category;
    }

    public Product(
            Integer code,
            String description,
            Double unitPrice,
            ManufacturingOrigin manufacturingOrigin,
            Category category,
            Boolean state) {
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.manufacturingOrigin = manufacturingOrigin;
        this.category = category;
        this.state = state;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public ManufacturingOrigin getManufacturingOrigin() {
        return manufacturingOrigin;
    }

    public void setManufacturingOrigin(ManufacturingOrigin manufacturingOrigin) {
        this.manufacturingOrigin = manufacturingOrigin;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Product product = (Product) object;
        return Objects.equals(code, product.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }

    @Override
    public String toString() {
        return "Product{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", manufacturingOrigin=" + manufacturingOrigin +
                ", category=" + category +
                ", state=" + state +
                '}';
    }
}
