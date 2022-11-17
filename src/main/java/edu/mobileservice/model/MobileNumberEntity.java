package edu.mobileservice.model;

import edu.mobileservice.model.annotations.Column;
import edu.mobileservice.model.annotations.PrimaryKey;
import edu.mobileservice.model.annotations.Table;

@Table(name = "mobile_number")
public class MobileNumberEntity {

    @PrimaryKey
    @Column(name = "id")
    private Integer id;
    @Column(name = "number", length = 45)
    private String number;
    @Column(name = "price_plan_id")
    private Integer pricePlanId;
    @Column(name = "user_id")
    private Integer userId;

    public MobileNumberEntity() {
    }

    public MobileNumberEntity(Integer id, String number, Integer pricePlanId, Integer userId) {
        this.id = id;
        this.number = number;
        this.pricePlanId = pricePlanId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getPricePlanId() {
        return pricePlanId;
    }

    public void setPricePlanId(Integer pricePlanId) {
        this.pricePlanId = pricePlanId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return String.format("\nID: %-5s Number: %-15s Price plan ID: %-5s User ID: %-1s", id, number, pricePlanId, userId);
    }
}
