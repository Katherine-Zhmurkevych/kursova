package edu.mobileservice.model;

import edu.mobileservice.model.annotations.Column;
import edu.mobileservice.model.annotations.PrimaryKey;
import edu.mobileservice.model.annotations.Table;

@Table(name = "users_have_mobile_devices")
public class UsersHaveMobileDevicesEntity {

    @PrimaryKey
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "mobile_device_id")
    private Integer mobileDeviceId;

    public UsersHaveMobileDevicesEntity() {
    }

    public UsersHaveMobileDevicesEntity(Integer userId, Integer mobileDeviceId) {
        this.userId = userId;
        this.mobileDeviceId = mobileDeviceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMobileDeviceId() {
        return mobileDeviceId;
    }

    public void setMobileDeviceId(Integer mobileDeviceId) {
        this.mobileDeviceId = mobileDeviceId;
    }

    @Override
    public String toString() {
        return String.format("\nUser ID: %-5s Mobile device ID: %-1s", userId, mobileDeviceId);
    }
}
