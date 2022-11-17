package edu.mobileservice.services;

import edu.mobileservice.model.MobileDeviceEntity;

import java.util.List;

public interface MobileDeviceManager {

    List<MobileDeviceEntity> findAllMobileDevices();

    MobileDeviceEntity findMobileDeviceById(final Integer id);

    boolean create(MobileDeviceEntity mobileDevice);

    boolean update(MobileDeviceEntity mobileDevice);

    boolean delete(MobileDeviceEntity mobileDevice);
}
