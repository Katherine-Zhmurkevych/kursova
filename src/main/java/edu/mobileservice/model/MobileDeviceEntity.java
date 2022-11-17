package edu.mobileservice.model;

import edu.mobileservice.model.annotations.Column;
import edu.mobileservice.model.annotations.PrimaryKey;
import edu.mobileservice.model.annotations.Table;

@Table(name = "mobile_device")
public class MobileDeviceEntity {

    @PrimaryKey
    @Column(name = "id")
    private Integer id;
    @Column(name = "brand", length = 45)
    private String brand;
    @Column(name = "model", length = 45)
    private String model;

    public MobileDeviceEntity() {
    }

    public MobileDeviceEntity(Integer id, String brand, String model) {
        this.id = id;
        this.brand = brand;
        this.model = model;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return String.format("\nID: %-5s Brand: %-10s Model: %-1s", id, brand, model);
    }
}
