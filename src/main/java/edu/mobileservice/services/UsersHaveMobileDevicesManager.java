package edu.mobileservice.services;

import edu.mobileservice.model.UsersHaveMobileDevicesEntity;

import java.util.List;


public interface UsersHaveMobileDevicesManager {

    List<UsersHaveMobileDevicesEntity> findAllUsersHaveMobileDevices();

    List<UsersHaveMobileDevicesEntity> findUsersHaveMobileDevicesByUserId(final Integer userId);

    boolean create(UsersHaveMobileDevicesEntity usersHaveMobileDevices);

    boolean update(UsersHaveMobileDevicesEntity usersHaveMobileDevices);

    boolean deleteUsersHaveMobileDevices(final UsersHaveMobileDevicesEntity usersHaveMobileDevices);
}
