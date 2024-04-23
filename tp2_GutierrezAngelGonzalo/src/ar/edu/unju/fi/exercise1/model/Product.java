package ar.edu.unju.fi.exercise1.model;

/**
 * @author Gutierrez Angel Gonzalo
 * @version 1.0
 */
public class Product {

    //#region Attributes
    private Integer code;
    private String description;
    private Double unitPrice;
    private ManufacturingOrigin manufacturingOrigin;
    private Category category;
    //#endregion

    //#region Constructors
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
    //#endregion

    //#region Getters and Setters
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
    //#endregion

    //#region Overriding methods
    @Override
    public String toString() {
        return "Product{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", manufacturingOrigin=" + manufacturingOrigin +
                ", category=" + category +
                '}';
    }
    //#endregion
}
