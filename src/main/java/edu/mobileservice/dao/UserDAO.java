package edu.mobileservice.dao;

import edu.mobileservice.model.MobileDeviceEntity;

import java.util.List;


public interface UserDAO {

    List<MobileDeviceEntity> findMobileDevicesByOwnerId(final Integer ownerId);
}
