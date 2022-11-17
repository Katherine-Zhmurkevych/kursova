package edu.mobileservice.services;

import edu.mobileservice.model.MobileDeviceEntity;
import edu.mobileservice.model.UserEntity;

import java.util.List;


public interface UserManager {

    List<UserEntity> findAllUsers();

    UserEntity findUser(final Integer id);

    List<MobileDeviceEntity> findMobileDevicesByOwner(final Integer ownerId);

    boolean create(final UserEntity user);

    boolean update(final UserEntity user);

    boolean deleteUser(final UserEntity user);
}
