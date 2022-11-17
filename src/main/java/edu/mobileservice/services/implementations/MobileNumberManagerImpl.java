package edu.mobileservice.services.implementations;

import edu.mobileservice.dao.implementations.MobileNumberDAOImpl;
import edu.mobileservice.model.MobileNumberEntity;
import edu.mobileservice.services.MobileNumberManager;

import java.util.List;
import java.util.Objects;


public class MobileNumberManagerImpl implements MobileNumberManager {

    private MobileNumberDAOImpl mobileNumberDAO = new MobileNumberDAOImpl();

    @Override
    public List<MobileNumberEntity> findAllMobileNumbers() {
        return mobileNumberDAO.findAll();
    }

    @Override
    public MobileNumberEntity findMobileNumber(Integer id) {
        final MobileNumberEntity number = (MobileNumberEntity) mobileNumberDAO.findByID(id);
        if (Objects.isNull(number)) {
            throw new IllegalStateException(String.format("Number with %s id doesn't exist", id));
        }
        return number;
    }

    @Override
    public boolean create(MobileNumberEntity number) {
        if (Objects.isNull(number)) {
            throw new IllegalStateException(String.format("Number doesn't exist"));
        }
        return mobileNumberDAO.create(number);
    }

    @Override
    public boolean update(MobileNumberEntity number) {
        if (Objects.isNull(number)) {
            throw new IllegalStateException(String.format("Number doesn't exist"));
        }
        return mobileNumberDAO.update(number);
    }

    @Override
    public boolean deleteMobileNumber(MobileNumberEntity number) {
        if (Objects.isNull(number)) {
            throw new IllegalStateException(String.format("Number doesn't exist"));
        }
        return mobileNumberDAO.delete(number.getId());
    }
}
