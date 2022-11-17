package edu.mobileservice.services.implementations;

import edu.mobileservice.dao.implementations.MobileDeviceDAOImpl;
import edu.mobileservice.model.MobileDeviceEntity;
import edu.mobileservice.services.MobileDeviceManager;

import java.util.List;
import java.util.Objects;

public class MobileDeviceManagerImpl implements MobileDeviceManager {

    private MobileDeviceDAOImpl mobileDeviceDAO = new MobileDeviceDAOImpl();

    @Override
    public List<MobileDeviceEntity> findAllMobileDevices() {
        return mobileDeviceDAO.findAll();
    }

    @Override
    public MobileDeviceEntity findMobileDeviceById(Integer id) {
        final MobileDeviceEntity mobileDevice = (MobileDeviceEntity) mobileDeviceDAO.findByID(id);
        if (Objects.isNull(mobileDevice)) {
            throw new IllegalStateException(String.format("Mobile device with %s id doesn't exists", id));
        }
        return mobileDevice;
    }

    @Override
    public boolean create(MobileDeviceEntity mobileDevice) {
        if (Objects.isNull(mobileDevice)) {
            throw new IllegalStateException(String.format("Mobile device doesn't exist"));
        }
        return mobileDeviceDAO.create(mobileDevice);
    }

    @Override
    public boolean update(MobileDeviceEntity mobileDevice) {
        if (Objects.isNull(mobileDevice)) {
            throw new IllegalStateException(String.format("Mobile device doesn't exist"));
        }
        return mobileDeviceDAO.update(mobileDevice);
    }

    @Override
    public boolean delete(MobileDeviceEntity mobileDevice) {
        if (Objects.isNull(mobileDevice)) {
            throw new IllegalStateException(String.format("Mobile device doesn't exist"));
        }
        return mobileDeviceDAO.delete(mobileDevice.getId());
    }
}
