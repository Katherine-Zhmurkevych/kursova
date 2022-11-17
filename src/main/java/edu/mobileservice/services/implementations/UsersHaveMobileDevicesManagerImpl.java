package edu.mobileservice.services.implementations;

import edu.mobileservice.dao.implementations.UsersHaveMobileDevicesDAOImpl;
import edu.mobileservice.model.UsersHaveMobileDevicesEntity;
import edu.mobileservice.services.UsersHaveMobileDevicesManager;

import java.util.List;
import java.util.Objects;

public class UsersHaveMobileDevicesManagerImpl implements UsersHaveMobileDevicesManager {

    private UsersHaveMobileDevicesDAOImpl usersHaveMobileDevicesDAO = new UsersHaveMobileDevicesDAOImpl();

    @Override
    public List<UsersHaveMobileDevicesEntity> findAllUsersHaveMobileDevices() {
        return usersHaveMobileDevicesDAO.findAll();
    }

    @Override
    public List<UsersHaveMobileDevicesEntity> findUsersHaveMobileDevicesByUserId(Integer userId) {
        return usersHaveMobileDevicesDAO.findUsersHaveMobileDevicesByUserId(userId);
    }

    @Override
    public boolean create(UsersHaveMobileDevicesEntity usersHaveMobileDevices) {
        if (Objects.isNull(usersHaveMobileDevices)) {
            throw new IllegalStateException(String.format("Such users which have such mobile devices don't exist"));
        }
        return usersHaveMobileDevicesDAO.create(usersHaveMobileDevices);
    }

    @Override
    public boolean update(UsersHaveMobileDevicesEntity usersHaveMobileDevices) {
        if (Objects.isNull(usersHaveMobileDevices)) {
            throw new IllegalStateException(String.format("Such users which have such mobile devices don't exist"));
        }
        return usersHaveMobileDevicesDAO.update(usersHaveMobileDevices);
    }

    @Override
    public boolean deleteUsersHaveMobileDevices(UsersHaveMobileDevicesEntity usersHaveMobileDevices) {
        if (Objects.isNull(usersHaveMobileDevices)) {
            throw new IllegalStateException(String.format("Such users which have such mobile devices don't exist"));
        }
        return usersHaveMobileDevicesDAO.deleteUsersHaveMobileDevicesOnUserIdAndMobileId
                (usersHaveMobileDevices.getUserId(),usersHaveMobileDevices.getMobileDeviceId());
    }
}
