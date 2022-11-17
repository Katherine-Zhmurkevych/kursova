package edu.mobileservice.model;

import edu.mobileservice.model.annotations.Column;
import edu.mobileservice.model.annotations.PrimaryKey;
import edu.mobileservice.model.annotations.Table;

import java.math.BigDecimal;

@Table(name = "price_plan")
public class PricePlanEntity {

    @PrimaryKey
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", length = 45)
    private String name;
    @Column(name = "specifications", length = 45)
    private String specifications;
    @Column(name = "price")
    private BigDecimal price;

    public PricePlanEntity() {
    }

    public PricePlanEntity(Integer id, String name, String specifications, BigDecimal price, Integer mobileNumberId) {
        this.id = id;
        this.name = name;
        this.specifications = specifications;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("\nID: %-5s Name: %-20s Specifications: %-40s Price: %-1s", id, name, specifications, price);
    }
}
